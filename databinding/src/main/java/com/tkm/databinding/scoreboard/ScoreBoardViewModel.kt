package com.tkm.databinding.scoreboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreBoardViewModel : ViewModel() {
    val teamAScore by lazy { MutableLiveData(0) }
    val teamBScore by lazy { MutableLiveData(0) }

    private var mLastTeamAScore = -1
    private var mLastTeamBScore = -1

    fun teamAPlus(score: Int) {
        saveLastTeamAScore()
        teamAScore.value?.let {
            teamAScore.value = it + score
        }
    }

    fun teamBPlus(score: Int) {
        saveLastTeamBScore()
        teamBScore.value?.let {
            teamBScore.value = it + score
        }
    }

    fun reset() {
        teamAScore.value = 0
        teamBScore.value = 0
    }

    fun undo() {
        //  只可撤销一次
        if (mLastTeamAScore != -1) {
            teamAScore.value = mLastTeamAScore
            mLastTeamAScore = -1

        }

        if (mLastTeamBScore != -1) {
            teamBScore.value = mLastTeamBScore
            mLastTeamBScore = -1
        }
    }

    private fun saveLastTeamAScore() {
        mLastTeamAScore = teamAScore.value ?: 0
    }

    private fun saveLastTeamBScore() {
        mLastTeamBScore = teamBScore.value ?: 0
    }
}