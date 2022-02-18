package com.example.norberbot.helper;

import com.google.common.collect.ImmutableMap;

public class AnswerHelper {
    private static final ImmutableMap<String, String> ANSWERS = new ImmutableMap.Builder<String, String>()
            .put("ayuda", """
                    Para :lupa:  buscar una definición debes que mencionarme, escribir la palabra deseada y finalizar con ' : '
                                            
                    Acá tienes un ejemplo, @Ideabot abuso:
                                            
                                            
                    Si deseas ver un listado de las palabras disponibles :página_doblada_por_abajo: , debes mencionarme y escribir la palabra ‘opciones’
                                            
                                          
                    Tambien podes acceder al glosario completo :libros: haciendo click en el siguiente enlace:
                                            
                                            
                    :eslabón: https://docs.google.com/spreadsheets/d/1DrQyGDxxDxlUKVW4xAdprgV04bs-FTmNCw5c40IXgfc/edit?usp=sharing
                    """).build();

    public static String getAnswer(String mention){
        return ANSWERS.get(mention);
    }
}
