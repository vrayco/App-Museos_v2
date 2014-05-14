package org.museosdetenerife;

import java.util.ArrayList;

import get.data.csv.CSV_Map;
import get.data.csv.DownloadCSV;
import get.data.csv.ImportCSVMuseos;
import get.data.rss.DownloadRSS;
import get.data.rss.ImportRSSMuseos;
import get.data.rss.Item;
import get.data.rss.RSS_Map;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class EventosListaFragment extends ListFragment {

	static String id_museo;
	static ArrayList<Item> lista_eventos_museo;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Globals g = (Globals) getActivity().getApplication();

		id_museo = g.getIdMuseo();
		DownloadRSS d_rss = null;
		ImportRSSMuseos rss_info = null;
		RSS_Map rss_map = null;

		try {
			d_rss = new DownloadRSS(getActivity().getApplicationContext());
			rss_info = new ImportRSSMuseos(d_rss.getRSSFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		rss_map = new RSS_Map(rss_info.getListaEventos());

		Integer num_eventos = rss_map.getNumeroEventosMuseo(id_museo);
		String[] values = new String[num_eventos];
		for (int i = 0; i < num_eventos; i++)
			values[i] = String.valueOf(i);
		// ArrayList<Item> lista_eventos =
		// rss_map.getListaCompletaEventosMuseo(id_museo);
		// String[] values = new String[] { "Evento 1", "Evento 2", "Evento 3",
		// "Evento 4" };
		setListAdapter(new EventosArrayAdapter(getActivity(), values, id_museo));

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// do something with the data

		DownloadRSS d_rss = null;
		ImportRSSMuseos rss_info = null;
		RSS_Map rss_map = null;

		try {
			d_rss = new DownloadRSS(getActivity().getApplicationContext());
			rss_info = new ImportRSSMuseos(d_rss.getRSSFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		rss_map = new RSS_Map(rss_info.getListaEventos());

		String id_evento = String.valueOf(position); 
		String titulo = rss_map.getEventField(id_museo, id_evento, "Title");
		String descripcion = rss_map.getEventField(id_museo, id_evento, "Description");
		String link = rss_map.getEventField(id_museo, id_evento, "Link");
		String id_lugar = rss_map.getEventField(id_museo, id_evento, "Lugar");
		String fecha_inicio = rss_map.getEventField(id_museo, id_evento, "Fecha");
		String fecha_fin = rss_map.getEventField(id_museo, id_evento, "FechaFin");
		String imagen = rss_map.getEventField(id_museo, id_evento, "Imagen");
		
		DownloadCSV d_csv = null;
		ImportCSVMuseos csv_info = null;
		CSV_Map csv_map = null;

		try {
			d_csv = new DownloadCSV(getActivity().getApplicationContext());
			csv_info = new ImportCSVMuseos(d_csv.getCSVFile());
		} catch (Exception e) {
			e.printStackTrace();
		}

		csv_map = new CSV_Map(csv_info.getListaMuseos());
		
		String lugar = csv_map.getMuseoField(id_lugar, "Nombre");
		
		Intent intent = new Intent(getActivity(), ItemEventoActivity.class);
		String selectedValue = (String) getListAdapter().getItem(position);
		//intent.putExtra("ID_evento", selectedValue);
		intent.putExtra("titulo", titulo);
		DatosEstaticos datos = new DatosEstaticos();
		intent.putExtra("subtitulo", datos.getTitulo(id_museo));
		intent.putExtra("contenido", descripcion);
		intent.putExtra("imagen_url", imagen);
		intent.putExtra("fecha_inicio", fecha_inicio);
		intent.putExtra("fecha_fin", fecha_fin);
		intent.putExtra("lugar", lugar);
		intent.putExtra("url", link);
		
		startActivity(intent);
	}

	public static void setIdMuseo(String _id_museo) {
		id_museo = _id_museo;
	}
}