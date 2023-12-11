package com.example.dam_resources_custom;

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.Map;

public class Utilitaires {
    /**
     * Création des outils pour gérer des resources custom au format XML
     * exemple :
     * <map id="">
     * <entry cle=""  valeur="">
     *
     * </entry>
     * </map>
     * L'utilitaire doit permettre de parser un fichier resources xml et
     * de créer un objet map
     */

    /*
    Get teh custom XML data  from its id and return a map translation
     */
    static public Map<String, String> readMapXML(Context c, String mapName) {

        //get resources xml data  and parse it
        //Activity class exposes an XML parser useful in this context
        //declare map object to return
        Map<String, String> decodeMap = new HashMap<String, String>();
        //get parser
        Context context = c;
        int mapId = context.getResources().getIdentifier("map","xml",context.getPackageName());
        XmlResourceParser parser = context.getResources().getXml(mapId);
        String cle = null, valeur = null;
        try {
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("entry")) {
                        cle = parser.getAttributeValue(null, "titre");
                        valeur = parser.getAttributeValue(null, "commentaire");
                        if (null == cle) {
                            parser.close();
                            return null;
                        }
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (parser.getName().equals("entry")) {
                        decodeMap.put(cle, valeur);
                        cle = null;
                        valeur = null;
                    }

                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return decodeMap;
    }


}
