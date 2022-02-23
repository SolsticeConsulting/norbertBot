package com.example.norberbot.helper;

public class AnswerHelper {
    private static final String HELP = """
            Para :lupa: buscar una definición debes que mencionarme, escribir la palabra deseada y finalizar con ' : '
                                    
            Acá tienes un ejemplo, @Ideabot abuso:
                                    
                                   
            Si deseas ver un listado de las palabras disponibles :página_doblada_por_abajo: , debes mencionarme y escribir la palabra ‘opciones’
                                    
                                  
            Tambien podes acceder al glosario completo :libros: haciendo click en el siguiente enlace:
                                    
                                    
            :eslabón: https://docs.google.com/spreadsheets/d/1DrQyGDxxDxlUKVW4xAdprgV04bs-FTmNCw5c40IXgfc/edit?usp=sharing
            """;

    private static final String OPTIONS_HEADER = "Palabras Disponibles en el glosario IDEA\n\n";

    private static final String NO_DEFINITIONS = """
            No encontré definiciones en el glosario de IDEA relacionadas con esa palabra.

            Las palabras disponibles en el glosario IDEA son las siguientes:
            """;

    private static final String DEFINITIONS_HEADER = "He encontrado las siguientes palabras, por favor selecciona una para leer su definicion: ";

    private static final String DEFINITION_HEADER = ":books: La definición de la palabra es la siguiente: \n\n";

    private static final String RESOURCE_HEADER = ":link: La fuente de la definición la encontráras en el siguiente enlace: ";

    public static String getHelpText() {
        return HELP;
    }

    public static String getOptionsHeaderText() {
        return OPTIONS_HEADER;
    }

    public static String getNoDefinitionsText() {
        return NO_DEFINITIONS;
    }

    public static String getDefinitionsHeader() {
        return DEFINITIONS_HEADER;
    }

    public static String getDefinitionHeader() {
        return DEFINITION_HEADER;
    }

    public static String getResourceHeader() {
        return RESOURCE_HEADER;
    }
}