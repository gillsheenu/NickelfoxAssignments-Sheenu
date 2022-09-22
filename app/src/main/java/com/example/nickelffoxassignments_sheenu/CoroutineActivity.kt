package com.example.nickelffoxassignments_sheenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {

    var TAG="KOTLINCOROUTINE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch{
//             Log.d(TAG, coroutineContext.toString())
            executes()
        }
    }
    private suspend fun executes() {


//        Log.d(TAG, coroutineContext.toString())
//        Log.d(TAG, "before")
//        GlobalScope.launch(Dispatchers.IO) {
//            var fb=async{fbFollowers()}
//            var insta=async {instaFollowers()  }
//            Log.d(TAG, "After fbFollowers-${fb.await()} --- instaFollowers-${insta.await()}")
//        }
//        job.join()


        //----------------------STRUCTURED CONCURRENCY-----------------------------

        val  parentJob= GlobalScope.launch(Dispatchers.Main){
//            Log.d(TAG, "Parent-$coroutineContext")

            Log.d(TAG,"Parent Started")
            val childJob=launch() {
//                Log.d(TAG,"Child-$coroutineContext")
                try {
                    Log.d(TAG, "Child JOb Started")
                    delay(5000)
                    Log.d(TAG,"Child Job Ended")
                }catch (c:CancellationException){
                    Log.d(TAG, "childJob Canceled")
                }

            }

            delay(3000)
            childJob.cancel()
//            Log.d(TAG, "childJob Canceled")
            Log.d(TAG,"Parent Ended")
        }

//        delay(1000)
//        parentJob.cancel()
//        Log.d(TAG, "Parent Job canceled")


        parentJob.join()
        Log.d(TAG, "parent job completed")


        /*--------------------WITHCONTEXT--------------------------------*/

        Log.d(TAG,"Before")
        withContext(Dispatchers.IO){
            delay(1000)
            Log.d(TAG,"Inside")
        }
        Log.d(TAG,"After")

    }



    suspend fun fbFollowers():Int{
        delay(2000)
        return 54
    }
    suspend fun instaFollowers():Int{
        delay(3000)
        return 113
    }

}