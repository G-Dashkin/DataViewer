package com.perfomax.dataviewer.presentation.home

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perfomax.dataviewer.domain.EMPTY
import com.perfomax.dataviewer.domain.usecases.feeds.CountFeedElementsUseCase
import com.perfomax.dataviewer.domain.usecases.feeds.GetAllFeedsUseCase
import com.perfomax.dataviewer.domain.usecases.projects.GetSelectedProjectUseCase
import com.perfomax.dataviewer.domain.usecases.scheduler.SetScheduleUseCase
import com.perfomax.dataviewer.domain.usecases.settings.GetUpdateIntoBackgroundUseCase
import com.perfomax.dataviewer.domain.usecases.settings.GetUpdatePeriodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val RECEIVER_FEED_ACTION = "com.perfomax.dataviewer.RECEIVER_FEED_ACTION"
private const val RECEIVER_FEED_EXTRAS = "receiver_feed_extras"
private const val RECEIVER_FEED_TIME_EXTRAS = "receiver_feed_time_extras"

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getAllFeedsUseCase: GetAllFeedsUseCase,
    private val getSelectedProjectUseCase: GetSelectedProjectUseCase,
    private val countFeedElementsUseCase: CountFeedElementsUseCase,

    private val setScheduleUseCase: SetScheduleUseCase,

    private val getUpdateIntoBackgroundUseCase: GetUpdateIntoBackgroundUseCase,
    private val getUpdatePeriodUseCase: GetUpdatePeriodUseCase,
): ViewModel(), HomeContract {


    private val _uiState = MutableStateFlow(HomeContract.State.initial())
    override val uiState: StateFlow<HomeContract.State> = _uiState.asStateFlow()

    private val _effect = MutableStateFlow<HomeContract.Effect?>(null)
    override val effect: StateFlow<HomeContract.Effect?> = _effect.asStateFlow()

    override fun intent(event: HomeContract.Event) {
        when(event) {
            is HomeContract.Event.ClickFeedNameEvent -> openDialogHomeScreenFeed(event.feedName)
            is HomeContract.Event.ClickFindFeedElement -> onFindFeedElementChange(event.findFeedElement)
            is HomeContract.Event.ChangeFeedEvent -> {  }

            HomeContract.Event.FindFeedElementsEvent -> {  }
            HomeContract.Event.CountFeedElementEvent -> countFeedElements()
            HomeContract.Event.UpdateFeedsListEvent -> updateFeedsList()
            HomeContract.Event.UpdateFeedEvent -> updateSelectedFeed()
            HomeContract.Event.UpdateBackgroundEvent -> testBackgroundUpdate()
            HomeContract.Event.CloseDialogClickEvent -> closeDialogHomeScreenFeed()
        }
    }

    init {
        testBackgroundUpdate()
        setSchedule()
    }

    private fun testBackgroundUpdate() {
        viewModelScope.launch {
            val intent = Intent()
            intent.action = RECEIVER_FEED_ACTION
            intent.putExtra(RECEIVER_FEED_EXTRAS, getUpdateIntoBackgroundUseCase.execute())
            intent.putExtra(RECEIVER_FEED_TIME_EXTRAS, getUpdatePeriodUseCase.execute())
            intent.setPackage(context.packageName)
            context.sendBroadcast(intent)
        }
    }

    private fun setSchedule() {
        viewModelScope.launch {
            setScheduleUseCase.execute(getUpdatePeriodUseCase.execute().toLong())
        }
    }

    override fun consume() {
        _effect.update { null }
    }

    private fun updateFeedsList() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    feedsList = getAllFeedsUseCase.execute(getSelectedProjectUseCase.execute())
                )
            }
        }
    }

    private fun updateSelectedFeed() {
        viewModelScope.launch {
            val allFeeds = getAllFeedsUseCase.execute(getSelectedProjectUseCase.execute())
            val selectedFeed = allFeeds.find { feed -> feed.feedName == uiState.value.selectedFeedName }
            closeDialogHomeScreenFeed()
            _uiState.update { currentState -> currentState.copy(isUpdatingFeedList = true) }
            countFeedElementsUseCase.execute(listOf(selectedFeed!!))
//            if ()
            _uiState.update { currentState -> currentState.copy(isUpdatingFeedList = false) }
            updateFeedsList()
        }
    }

    private fun countFeedElements() {
        viewModelScope.launch {
            _uiState.update { currentState -> currentState.copy(isUpdatingFeedList = true) }
            countFeedElementsUseCase.execute(_uiState.value.feedsList)
            _uiState.update { currentState -> currentState.copy(isUpdatingFeedList = false) }
            updateFeedsList()
        }
    }

    private fun openDialogHomeScreenFeed(feedName: String) {
        viewModelScope.launch {
            val allFeeds = getAllFeedsUseCase.execute(getSelectedProjectUseCase.execute())
            val selectedFeedUrl = allFeeds.find { feed -> feed.feedName == feedName }?.feedUrl
            _uiState.update { currentState ->
                currentState.copy(
                    openDialogHomeScreenFeed = true,
                    selectedFeedName = feedName,
                    selectedFeedUrl = selectedFeedUrl?: EMPTY
                )
            }
        }
    }

    private fun closeDialogHomeScreenFeed() {
        _uiState.update { currentState ->
            currentState.copy(
                openDialogHomeScreenFeed = false
            )
        }
        onClearFindFeedElementState()
    }

    private fun onClearFindFeedElementState(){
        _uiState.update { currentState ->
            currentState.copy(
                findFeedElement = EMPTY
            )
        }
    }

    private fun onFindFeedElementChange(text: String) {
        _uiState.update { currentState ->
            currentState.copy(
                findFeedElement = text
            )
        }
    }



}