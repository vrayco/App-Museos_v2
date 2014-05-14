package org.museosdetenerife;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

import android.app.Dialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;*/

//import org.json.JSONObject;


public class IrActivity extends FragmentActivity implements LocationListener
{
	
	public GoogleMap mapa = null;
	
	public double latitud = 28.489514;
	public double longitud = -16.314971;
	
	public String nombre = "Museo X";
	//private int vista = 0;
	
	public LatLng myPosition; 
	
//	public LatLng M_LERCARO = new LatLng( 28.489514, -16.314971);
//	public LatLng M_COSMOS = new LatLng( 28.476029, -16.309120);
//	public LatLng M_NATURALEZA = new LatLng( 28.463834, -16.249156);
//	public LatLng M_CEDOCAM = new LatLng( 28.490367, -16.314537);
//	public LatLng M_CASTILLO = new LatLng( 28.467866, -16.247182);
//	public LatLng M_CARTA = new LatLng( 28.514245, -16.390116);
	
	String id_museo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ir);
		
		Intent intent = getIntent();
		String latitud_str = intent.getStringExtra("latitud");
		String longitud_str = intent.getStringExtra("longitud");
		
		latitud = Double.parseDouble(latitud_str);
		longitud = Double.parseDouble(longitud_str);
		nombre = intent.getStringExtra("nombre");
		
		// Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
		
		if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }//else{
		
		
		//setUpMapIfNeeded();

		//Intent intent = getIntent();
		//id_museo = intent.getStringExtra("ID");
		
		//final ActionBar actionBar = getActionBar();

		/*Titulos titulo = new Titulos();
		actionBar.setTitle(titulo.getTitulo(id_museo));
		if(titulo.containsKeySubtitulo(id_museo))
			actionBar.setSubtitle(titulo.getSubtitulo(id_museo));
		*/
		//Marker MakerLercaro = mapa.addMarker(new MarkerOptions()
	    //   .position(M_LERCARO)
	    //   .title("MHA - Casa Lercaro")  //Agrega un titulo al marcador
	    //   .snippet("Como llegar")   //Agrega informaci�n detalle relacionada con el marcador 
	    //   .icon(BitmapDescriptorFactory.fromResource(R.drawable.anthropologia)));
		
		onInfoWindowClick();
	}
	
	
	/*private void setUpMapIfNeeded() {
		// TODO Auto-generated method stub
		if (mapa == null) {
		      //Instanciamos el objeto mMap a partir del MapFragment definido bajo el Id "map"
			   mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		      // Chequeamos si se ha obtenido correctamente una referencia al objeto GoogleMap
		      if (mapa != null) {
		        // El objeto GoogleMap ha sido referenciado correctamente 
		        //ahora podemos manipular sus propiedades
		        
		        //Seteamos el tipo de mapa 
		        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		        	
		        //Activamos la capa o layer MyLocation
		        mapa.setMyLocationEnabled(true);
		        
		        
	    }
		}
	}*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ir, menu);
		return true;
	}
	
	public void InfoActivity(View view) {
	    
		Intent intent;
		
		intent = new Intent(this, MuseoActivity.class);
		intent.putExtra("ID", id_museo);
			
		startActivity(intent);

	}
	
	public void EventosActivity(View view) {
		Intent intent;
		
		intent = new Intent(this, EventosActivity.class);
		intent.putExtra("ID", id_museo);
			
		startActivity(intent);
	}
	
	public void IrActivity(View view) {
		Intent intent;
		
		intent = new Intent(this, IrActivity.class);
		intent.putExtra("ID", id_museo);
			
		startActivity(intent);
	}
	
	private void onInfoWindowClick() {
		// TODO Auto-generated method stub
		double lat = latitud;
		double longi = longitud;
		
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        
        Criteria criteria = new Criteria();
        
        String provider = locationManager.getBestProvider(criteria, true);
        
        Location location = locationManager.getLastKnownLocation(provider);
        
        if(location != null){
        	
        	onLocationChanged(location);
        }
		
      /*  double mlatitude = location.getLatitude();
    	double mlongitude = location.getLongitude();
    	
    	LatLng latlng = new LatLng(mlatitude, mlongitude);
    	
    	myPosition = new LatLng(mlatitude, mlongitude);
    	
    	mapa.addMarker(new MarkerOptions()
		.position(myPosition)
		.title("Estas aqu�"));
*/		 
		Uri geoLocation = Uri.parse("geo:0,0?q="+lat+","+longi+"("+nombre+")");
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(geoLocation);

		if (intent.resolveActivity(getPackageManager()) != null) {
			startActivity(intent);
		}

		
	}
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub        
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub        
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub        
    }

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	} 

}
