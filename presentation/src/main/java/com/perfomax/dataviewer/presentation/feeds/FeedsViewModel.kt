package com.perfomax.dataviewer.presentation.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.usecases.feeds.CountFeedElementsUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.GetAllFeedsUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.LoadFeedUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.RemoveFeedUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.SaveFeedUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetSelectedProjectUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class FeedsViewModel @Inject constructor(
    private val getSelectedProjectUseCase: GetSelectedProjectUseCase,
    private val saveFeedUseCase: SaveFeedUseCase,
    private val getAllFeedsUseCase: GetAllFeedsUseCase,
    private val loadFeedUseCase: LoadFeedUseCase,
    private val removeFeedUseCase: RemoveFeedUseCase,
    private val countFeedElementsUseCase: CountFeedElementsUseCase
): ViewModel(), FeedsContract {

    private val _uiState = MutableStateFlow(FeedsContract.State.initial())
    override val uiState: StateFlow<FeedsContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<FeedsContract.Effect?>(null)
    override val effect: StateFlow<FeedsContract.Effect?> = _effect.asStateFlow()

    init {
        _uiState.update { currentState->
            currentState.copy(
//                feedUrl = "https://citilink.ru"
//                feedUrl = "citilink.в"
                feedUrl = "https://feeds-mic.s1.citilink.ru/yandex_offer/spb_cl.xml"
//                feedUrl = "https://feeds-mic.s1.citilink.ru/yandex_offer/spb_cl.xml"
//                feedUrl = "https://api2.kiparo.com/static/it_news.xml"
            )
        }
    }

    override fun intent(event: FeedsContract.Event) {
        when(event) {
            is FeedsContract.Event.FeedUrlChangeEvent -> onFeedUrlChangeEvent(event.feedUrl)
            is FeedsContract.Event.SelectFeedElementEvent -> onSelectFeedElement(event.selectedFeedElement)
            is FeedsContract.Event.FeedNameEvent -> onFeedName(event.feedName)
            is FeedsContract.Event.SelectRemovedFeedEvent -> onSelectRemovedFeed(event.removedFeed)
            is FeedsContract.Event.SelectFeedDateElementEvent -> onSelectFeedDateElement(event.selectedFeedDataElement)

            is FeedsContract.Event.OpenChangeFeedDialogEvent -> openDialogChangeFeed(event.feedName)

            FeedsContract.Event.CloseDialogChangeFeedEvent -> closeDialogChangeFeed()

            FeedsContract.Event.AddFeedClickEvent -> onAddFeedClick()
            FeedsContract.Event.OpenDialogSelectedFeedElementEvent -> openDialogSelectedFeedElement()
            FeedsContract.Event.CloseDialogSelectedFeedElementEvent -> closeDialogSelectedFeedElement()
            FeedsContract.Event.AddNewFeedEvent -> onAddNewFeed()
            FeedsContract.Event.CloseDialogRemoveEvent -> closeDialogRemoveFeed()
            FeedsContract.Event.RemoveFeedClickEvent -> onRemoveFeed()
            FeedsContract.Event.UpdateProjectEvent -> loadFeedsList()
            FeedsContract.Event.SwitchScreenToFeedsListEvent -> onSwitchToFeedsList()
            FeedsContract.Event.CloseDialogFeedUrlErrorEvent -> closeDialogFeedUrlError()
            FeedsContract.Event.SelectDateElementInFeedEvent -> onSelectDateElementInFeed()

        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun onClearUiFieldsState(){
        _uiState.update { currentState ->
            currentState.copy(
                feedName = "",
                selectedFeedElement = ""
            )
        }
    }

    private fun loadFeedsList() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    feedsList = getAllFeedsUseCase.execute(getSelectedProjectUseCase.execute())
                )
            }
        }
    }

    private fun onFeedUrlChangeEvent(text: String) {
        _uiState.update { currentState ->
            currentState.copy(
                feedUrl = text,
                feedUrlError = text.isNotBlank()
            )
        }
    }

    private fun onAddFeedClick() {
        viewModelScope.launch {

            val feedUrl = _uiState.value.feedUrl
            onSwitchToFeedElements()
            val allFeedsByProjectsBy = getAllFeedsUseCase.execute(getSelectedProjectUseCase.execute())
            if(allFeedsByProjectsBy.find { it.feedUrl == feedUrl } == null ){

                val loadedFeed = loadFeedUseCase.execute(feedUrl)

                if(loadedFeed.any{ it.contains("errorURl") }) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            feedUrlError = true,
                            feedUrlErrorMessage = "Введен некорректный URl"
                        )
                    }
                } else if(
                    loadedFeed.any{ it.contains("<head>") } ||
                    loadedFeed.any{ it.contains("<body>") }
                ) {
                    _uiState.update { currentState ->
                        currentState.copy(
                            feedUrlError = true,
                            feedUrlErrorMessage = "По ссылке обнаружены HTML элементы. Возможно по ссылке не фид сайт, а сайт."
                            )
                        }
                    _uiState.update { currentState ->
                        currentState.copy(
                            loadedFeed = loadedFeed
                        )
                    } } else {
                        _uiState.update { currentState ->
                            currentState.copy(
                                loadedFeed = loadedFeed
                        )
                    }
                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        openDialogFeedUrlErrorElement = true
                    )
                }
            }
        }

    }

    private fun openDialogSelectedFeedElement() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogSelectedFeedElement = true
            )
        }
    }

    private fun closeDialogSelectedFeedElement() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogSelectedFeedElement = false
            )
        }
    }

    private fun closeDialogFeedUrlError() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogFeedUrlErrorElement = false
            )
        }
        onSwitchToFeedsList()
    }

    private fun onSelectFeedElement(selectedElement: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedFeedElement = selectedElement
            )
        }
    }

    private fun onSelectFeedDateElement(selectedDateElement: String) {
        _uiState.update { currentState ->
            currentState.copy(
                feedDateElement = selectedDateElement
            )
        }
    }

    private fun onFeedName(feedName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                feedName = feedName
            )
        }
    }

    private fun onSelectRemovedFeed(removeFeedName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                removedFeed = removeFeedName
            )
        }
        openDialogRemoveFeed()
    }

    private fun onRemoveFeed() {
        viewModelScope.launch {
            removeFeedUseCase.execute(_uiState.value.removedFeed)
            loadFeedsList()
        }
        closeDialogRemoveFeed()
    }

    private fun openDialogRemoveFeed() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogRemoveFeed = true,
            )
        }
    }

    private fun closeDialogRemoveFeed() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogRemoveFeed = false,
            )
        }
    }

    private fun onSwitchToFeedsList(){
        _uiState.update { currentState ->
            currentState.copy(
                isFeedsList = true
            )
        }
    }

    private fun onSwitchToFeedElements(){
        _uiState.update { currentState ->
            currentState.copy(
                isFeedsList = false
            )
        }
    }


    private fun onSelectDateElementInFeed() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogSelectedFeedElement = false,
                isSelectingFeedDateElement = true
            )
        }
    }

    private fun onAddNewFeed() {
        viewModelScope.launch {
            val project = getSelectedProjectUseCase.execute()
            val feedName = _uiState.value.feedName.trim()
            val feedElement = _uiState.value.selectedFeedElement
            val feedElementCount = 0
            val feedUrl = _uiState.value.feedUrl
            val feedUpdateTime = _uiState.value.feedDateElement

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val instant = Instant.ofEpochMilli(System.currentTimeMillis())
            val date = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            val feedLoadTime = formatter.format(date)

            val feed = "projectName:$project;" +
                       "feedName:$feedName;" +
                       "feedElement:$feedElement;" +
                       "feedElementCount:$feedElementCount;" +
                       "feedUrl:$feedUrl;" +
                       "feedUpdateTime:$feedUpdateTime;" +
                       "feedLoadTime:$feedLoadTime"

            val feedNameValid = feedName.isNotEmpty()
            val feedNameValid2 = feedName.any {
                it.toString().contains("|") ||
                        it.toString().contains(";") ||
                        it.toString().contains(",")
            }
            val feedNameValid3 = _uiState.value.feedsList.find { it.feedName == feedName }

            val feedElementValid = feedElement.isNotEmpty()

            if (feedNameValid && !feedNameValid2 && feedNameValid3 == null && feedElementValid) {
                saveFeedUseCase.execute(feed)
                onClearUiFieldsState()
                closeDialogSelectedFeedElement()
                loadFeedsList()
                onSwitchToFeedsList()

                _uiState.update {currentState -> currentState.copy(isCountingFeedElements = true)}
                val project2 = getSelectedProjectUseCase.execute()
                val allFeeds = getAllFeedsUseCase.execute(project2)
                val newFeed = allFeeds.find { it.feedName == feedName }
                countFeedElementsUseCase.execute(listOf(newFeed!!))
                _uiState.update {currentState -> currentState.copy(isCountingFeedElements = false)}

            } else if(feedNameValid2) {
                _uiState.update { state ->
                    FeedsContract.State.notCorrect()
                    state.copy(
                        feedNameError = feedName.any {
                            it.toString().contains("|") ||
                                    it.toString().contains(";") ||
                                    it.toString().contains(",")
                        },
                        feedNameErrorMessage = "Название фида не должно содержать знаков | ; ,"
                    )
                }

            } else if (feedNameValid3 != null) {
                _uiState.update { state ->
                    FeedsContract.State.notCorrect()
                    state.copy(
                        feedNameError = true,
                        feedNameErrorMessage = "Фид с таким названием уже загружен в этом проекте"
                    )
                }
            } else if (!feedElementValid) {
                _uiState.update { state ->
                    FeedsContract.State.notCorrect()
                    state.copy(
                        selectedFeedElementError = true,
                        selectedFeedElementErrorMessage = "Поле не должно быть пустым"
                    )
                }
            } else {
                _uiState.update { state ->
                    FeedsContract.State.notCorrect()
                    state.copy(
                        feedNameError = feedName.isEmpty(),
                        feedNameErrorMessage = "Поле не должно быть пустым"
                    )
                }

            }

        }
    }


    private fun openDialogChangeFeed(feedName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                feedName = feedName,
                openDialogChangeFeed = true
            )
        }
    }

    private fun closeDialogChangeFeed() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogChangeFeed = false
            )
        }
    }

}