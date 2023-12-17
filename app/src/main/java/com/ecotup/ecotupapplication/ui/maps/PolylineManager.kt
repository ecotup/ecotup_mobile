package com.ecotup.ecotupapplication.ui.maps

import androidx.loader.content.AsyncTaskLoader
import com.ecotup.ecotupapplication.BuildConfig
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.Polyline


public class PolylineManager {
    companion object{
        private var mMap: GoogleMap? = null
        private var destination: LatLng? = null
        private var source: LatLng? = null
        private var polyline: Polyline? = null
        private var boundNorthEast: LatLng? = null
        private var boundSouthWest: LatLng? = null
        private var distanceMarkerLatLng: LatLng? = null
        private var distanceMarker: Marker? = null
        private var distance: String? = null

        fun setMap(mMap: GoogleMap?) {
            this.mMap = mMap
        }

        fun setSource(source: LatLng?) {
            this.source = source
        }

        fun setDestination(destination: LatLng?) {
            this.destination = destination
        }

        fun getURL(from : LatLng?, to : LatLng?) : String {
            val origin = "origin=" + from?.latitude + "," + from?.longitude
            val dest = "destination=" + to?.latitude + "," + to?.longitude
            val mode = "mode=driving"
            val sensor = "sensor=false"
            val key = "key=AIzaSyCzbzneJg7Hovad7BdJIHUxH2KWricaw7U"
            val params = "$origin&$dest&$mode&$sensor&$key"
            return "https://maps.googleapis.com/maps/api/directions/json?$params"
        }

        fun decodePoly(encoded: String): List<LatLng> {
            val poly = ArrayList<LatLng>()
            var index = 0
            val len = encoded.length
            var lat = 0
            var lng = 0

            while (index < len) {
                var b: Int
                var shift = 0
                var result = 0
                do {
                    b = encoded[index++].toInt() - 63
                    result = result or (b and 0x1f shl shift)
                    shift += 5
                } while (b >= 0x20)
                val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
                lat += dlat

                shift = 0
                result = 0
                do {
                    b = encoded[index++].toInt() - 63
                    result = result or (b and 0x1f shl shift)
                    shift += 5
                } while (b >= 0x20)
                val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
                lng += dlng

                val p = LatLng(lat.toDouble() / 1E5,
                    lng.toDouble() / 1E5)
                poly.add(p)
            }

            return poly
        }
    }
}