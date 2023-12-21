package com.ecotup.ecotupapplication.util.directionhelper

import android.content.Context
import android.graphics.Color
import android.os.AsyncTask
import android.util.Log
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import org.json.JSONObject


class PointsParser(mContext: Context, directionMode: String) :
    AsyncTask<String?, Int?, List<List<HashMap<String, String>>>?>() {
    var taskCallback: TaskLoadedCallback
    var directionMode = "driving"

    init {
        taskCallback = mContext as TaskLoadedCallback
        this.directionMode = directionMode
    }

    override fun doInBackground(vararg p0: String?): List<List<HashMap<String, String>>>? {
        val jObject: JSONObject
        var routes: List<List<HashMap<String, String>>>? = null
        try {
            jObject = JSONObject(p0[0])
            Log.d("mylog", p0[0].toString())
            val parser = DataParser()
            Log.d("mylog", parser.toString())

            routes = parser.parse(jObject)
            Log.d("mylog", "Executing routes")
            Log.d("mylog", routes.toString())
        } catch (e: Exception) {
            Log.d("mylog", e.toString())
            e.printStackTrace()
        }
        return routes
    }

    override fun onPostExecute(result: List<List<HashMap<String, String>>>?) {
        var points: ArrayList<LatLng?>
        var lineOptions: PolylineOptions? = null

        for (i in result!!.indices) {
            points = ArrayList()
            lineOptions = PolylineOptions()

            val path = result[i]

            for (j in path.indices) {
                val point = path[j]
                val lat = point["lat"]!!.toDouble()
                val lng = point["lng"]!!.toDouble()
                val position = LatLng(lat, lng)
                points.add(position)
            }

            lineOptions.addAll(points)
            if (directionMode.equals("walking", ignoreCase = true)) {
                lineOptions.width(10f)
                lineOptions.color(Color.MAGENTA)
            } else {
                lineOptions.width(20f)
                lineOptions.color(Color.parseColor("#2CBE21"))
            }
            Log.d("mylog", "onPostExecute lineoptions decoded")
        }


        if (lineOptions != null) {
            //mMap.addPolyline(lineOptions);
            taskCallback.onTaskDone(lineOptions)
        } else {
            Log.d("mylog", "without Polylines drawn")
        }
    }
}