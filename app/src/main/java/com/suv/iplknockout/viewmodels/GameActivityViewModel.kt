package com.suv.iplknockout.viewmodels

import androidx.lifecycle.ViewModel
import java.lang.IndexOutOfBoundsException
import java.lang.NullPointerException
import javax.inject.Inject

class GameActivityViewModel @Inject constructor() : ViewModel() {

    // Making pairs of teams by random selection from list of teams, works with any even number of list
    suspend fun getPairs(teamList: List<String>): ArrayList<ArrayList<String>>? {
        val sizeOfList = teamList.size
        val setList = LinkedHashSet<Int>()
        if (sizeOfList % 2 == 0) {
            try {
                while (setList.size != sizeOfList) {
                    val number = (0 until sizeOfList).random()
                    setList.add(number)
                }

                val randomTeamList = ArrayList<String>()
                setList.forEach {
                    randomTeamList.add(teamList[it])
                }

                val pairedList = ArrayList<ArrayList<String>>()
                var setPosition = 0
                for (i in 0 until (teamList.size / 2)) {
                    pairedList.add(arrayListOf(randomTeamList[setPosition], randomTeamList[++setPosition]))
                    setPosition++
                }

                return pairedList

            } catch (indexOutOfBounds: IndexOutOfBoundsException) {
                indexOutOfBounds.printStackTrace()
            } catch (nullPointer: NullPointerException) {
                nullPointer.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return null
    }

    // any random number from 0 and 1 will be selected as winner for that pair
    fun getWinner() :Int {
        return (0..1).random()
    }

}