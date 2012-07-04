package api.com.bairos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rodrigo Bairos
 */
public class Datas {

    Date Data2;
    String DataConvertida;
    SimpleDateFormat DataFormatada = new SimpleDateFormat("yyyy/MM/dd");
    SimpleDateFormat DataFormatada2 = new SimpleDateFormat("dd/MM/yyyy");

    public String ConverterData(String Data, String Linguagem, String Local) {
        try {
            Data2 = DataFormatada.parse(Data);
        } catch (ParseException ex) {
            System.err.println("Error to parse the typed date: " + ex);
        }
        /*
         * if ((Data == null) || (Linguagem == null) || (Local == null)) { throw
         * new IllegalArgumentException("parameter cannot be null");
        }//
         */
        if (LocaleISOData.isoLanguageTable.contains(Linguagem)) {
            DateFormat DF = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, new Locale(Linguagem, Local));
            DataConvertida = DF.format(Data2);
        } else {
            throw new IllegalArgumentException("The \"" + Linguagem + "\" encoding ISO not available!");
        }
        if (LocaleISOData.isoCountryTable.contains(Local)) {
            DateFormat DF = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM, new Locale(Linguagem, Local));
            DataConvertida = DF.format(Data2);
        } else {
            throw new IllegalArgumentException("The \"" + Local + "\" encoding ISO not available!");
        }
        return DataConvertida;
    }

    public String ConverterDataPorExtenso(String Data, String Linguagem, String Local) {
        try {
            Data2 = DataFormatada2.parse(Data);
        } catch (ParseException ex) {
            System.err.println("Error to parse the typed date: " + ex);
        }

        if ((Data == null) || (Local == null)) {
            throw new IllegalArgumentException("parameter cannot be null");
        }
        if ((Linguagem == null) || (Linguagem.equals(""))) {
            DateFormat DF = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, Locale.getDefault());
            DataConvertida = DF.format(Data2);
        } else if (LocaleISOData.isoCountryTable.contains(Local)) {
            DateFormat DF = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, new Locale(Linguagem, Local));
            DataConvertida = DF.format(Data2);
        } else {
            throw new IllegalArgumentException("The \"" + Local + "\" encoding ISO not available!");
        }
        return DataConvertida;
    }

    public static void main(String[] args) {
        System.out.println(new Datas().ConverterData("2012/06/10", "pt", "BR"));
        System.out.println(new Datas().ConverterDataPorExtenso("10/06/2012", "en", "US"));

    }
}
