package org.museosdetenerife;


import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import get.data.rss.DownloadRSS;
import get.data.rss.ImportRSSMuseos;
import get.data.rss.RSS_Map;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventosArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] values;
	private final String id_museo;
 
	public EventosArrayAdapter(Context context, String[] values, String id_museo) {
		super(context, R.layout.lista_museo, values);
		this.context = context;
		this.values = values;
		this.id_museo = id_museo;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.lista_eventos, parent, false);
		TextView titulo = (TextView) rowView.findViewById(R.id.titulo);
		TextView subtitulo = (TextView) rowView.findViewById(R.id.subtitulo);
		ImageView foto = (ImageView) rowView.findViewById(R.id.foto);
 
		// Change icon based on name
		String id_evento = values[position];

	    DownloadRSS d_rss = null;
		ImportRSSMuseos rss_info = null;
		RSS_Map rss_map = null;

		try {
			d_rss = new DownloadRSS(context);
			rss_info = new ImportRSSMuseos(d_rss.getRSSFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		rss_map = new RSS_Map(rss_info.getListaEventos());
		
		String titulo_str = rss_map.getEventField(id_museo, id_evento, "Title");
		if(titulo_str.length() > 43)
		    titulo_str = titulo_str.substring(0,45) + "...";
		
		String subtitulo_str = rss_map.getEventField(id_museo, id_evento, "Fecha");
		String foto_url = rss_map.getEventField(id_museo, id_evento, "Imagen");
		titulo.setText(titulo_str);
		subtitulo.setText(subtitulo_str);	
		UrlImageViewHelper.setUrlDrawable(foto, foto_url, R.drawable.icon);
		
		return rowView;
	}
	

}