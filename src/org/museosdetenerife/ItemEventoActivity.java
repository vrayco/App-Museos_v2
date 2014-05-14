package org.museosdetenerife;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemEventoActivity extends Activity {

	String id_museo;
	String mas_info = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_evento);
		
		
		Intent intent = getIntent();
		id_museo = intent.getStringExtra("ID");
		String titulo = intent.getStringExtra("titulo");
		String subtitulo = intent.getStringExtra("subtitulo");
		String contenido = intent.getStringExtra("contenido");
		String imagen_url = intent.getStringExtra("imagen_url");
		String fecha_inicio = intent.getStringExtra("fecha_inicio");
		String fecha_fin = intent.getStringExtra("fecha_fin");
		String lugar = intent.getStringExtra("lugar");
		this.mas_info = intent.getStringExtra("url");
		
		TextView texto_contenido = (TextView) findViewById(R.id.texto_evento);
		texto_contenido.setText(Html.fromHtml(contenido));
		
		TextView TV_fecha_inicio = (TextView) findViewById(R.id.fecha_inicio);
		TV_fecha_inicio.setText(fecha_inicio);
		
		TextView TV_fecha_fin = (TextView) findViewById(R.id.fecha_fin);
		TV_fecha_fin.setText(fecha_fin);
		
		TextView TV_lugar = (TextView) findViewById(R.id.lugar);
		TV_lugar.setText(lugar);
		
		ImageView foto = (ImageView) findViewById(R.id.foto);
		UrlImageViewHelper.setUrlDrawable(foto, imagen_url, R.drawable.icon);
		
		final ActionBar actionBar = getActionBar();

		actionBar.setTitle(titulo);
		actionBar.setSubtitle(subtitulo);
		
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.item_evento, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {   
		// El boton de volver al listado de eventos
		onBackPressed();
	    return true;
    }
	
	public void irAWeb(View view) {
		String url = mas_info;
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}	

}
