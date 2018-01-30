package com.example.jsonacdat.utils;

import com.example.jsonacdat.pojo.Contacto;
import com.example.jsonacdat.pojo.Telefono;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
}
