package com.example.jsonacdat.utils;

import android.os.Environment;
import android.util.Log;

import com.example.jsonacdat.pojo.Contacto;
import com.example.jsonacdat.pojo.Noticia;
import com.example.jsonacdat.pojo.Telefono;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Análisis de JSON
 */

public class AnalisisJSON {

    public static String analizarPrimitiva(JSONObject texto) throws JSONException {
        JSONArray jsonContenido;
        JSONObject item;
        String tipo;
        StringBuilder cadena = new StringBuilder();
        tipo = texto.getString("info");
        jsonContenido = new JSONArray(texto.getString("sorteo"));
        cadena.append("Sorteos de la Primitiva:" + "\n");

        for (int i = 0; i < jsonContenido.length(); i++) {
            item = jsonContenido.getJSONObject(i);
            cadena.append(tipo + ":" + item.getString("fecha") + "\n");
            cadena.append(
                    item.getInt("numero1") + ", " +
                    item.getInt("numero2") + ", " +
                    item.getInt("numero3") + ", " +
                    item.getInt("numero4") + ", " +
                    item.getInt("numero5") + ", " +
                    item.getInt("numero6") + "\n");
            cadena.append("Complementario " + item.getInt("complementario")  + "\n");
            cadena.append("Reintegro " + item.getInt("reintegro")  + "\n\n");
        }
        return cadena.toString();
    }

    public static ArrayList<Contacto> analizarContactos(JSONObject texto) throws JSONException {

        ArrayList<Contacto> listaContactos = new ArrayList<>();

        JSONArray jsonContenido;
        JSONObject contactoJSON;
        JSONObject telefonoJSON;
        Contacto nuevoContacto;
        jsonContenido = new JSONArray(texto.getString("contactos"));

        for (int i = 0; i < jsonContenido.length(); i++) {

            contactoJSON = jsonContenido.getJSONObject(i);

            nuevoContacto = new Contacto();
            nuevoContacto.setNombre(contactoJSON.getString("nombre"));
            nuevoContacto.setDireccion(contactoJSON.getString("direccion"));
            nuevoContacto.setEmail(contactoJSON.getString("email"));

            Telefono nuevosTelefonos = new Telefono();
            telefonoJSON = contactoJSON.getJSONObject("telefono");

            nuevosTelefonos.setCasa(telefonoJSON.getString("casa"));
            nuevosTelefonos.setMovil(telefonoJSON.getString("movil"));
            nuevosTelefonos.setTrabajo(telefonoJSON.getString("trabajo"));

            nuevoContacto.setTelefonos(nuevosTelefonos);

            listaContactos.add(nuevoContacto);
        }
        return listaContactos;
    }

    public static void escribirJSON(ArrayList<Noticia> noticias, String fichero) throws IOException, JSONException
    {
        OutputStreamWriter out;
        File miFichero;
        JSONObject objeto;
        JSONObject rss;
        JSONArray lista;
        miFichero = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fichero);
        out = new FileWriter(miFichero);
        //crear objeto JSON
        objeto = new JSONObject();
        objeto.put("web", "https://geekstorming.wordpress.com/");
        objeto.put("link", "https://geekstorming.wordpress.com/feed");

        rss = new JSONObject();

        lista = new JSONArray();

        for (int i = 0; i < noticias.size(); i++) {
            JSONObject titular = new JSONObject();
            titular.put("titulo", noticias.get(i).getTitle());
            titular.put("fecha", noticias.get(i).getPubDate());
            titular.put("link", noticias.get(i).getLink());
            titular.put("descripcion", noticias.get(i).getDescription());

            lista.put(titular);
        }

        objeto.put("titulares", lista);
        rss.put("rss", objeto);
        out.write(rss.toString(4)); //tabulación de 4 caracteres
        out.flush();
        out.close();
        Log.i("info", objeto.toString());
    }
}
