package com.example.norberbot.helper;

public class AnswerHelper {
    private static final String HELP = """
            ¡Hola! Soy Bob el Deconstructor :derelict_house_building: :rainbow:.
                        
            Para :mag: buscar una definición debes que mencionarme, escribir la palabra deseada y finalizar con ' : '
                                    
            Acá tienes un ejemplo, @Bot_El_Deconstructor abuso:
                                    
                                   
            Si deseas ver un listado de las palabras disponibles :page_with_curl:, debes mencionarme y escribir la palabra ‘opciones’
                                    
                                  
            También podrás acceder al glosario completo :books: haciendo click en el siguiente enlace:
                                    
                                    
            :link: https://docs.google.com/spreadsheets/d/1DrQyGDxxDxlUKVW4xAdprgV04bs-FTmNCw5c40IXgfc/edit?usp=sharing
            """;

    private static final String OPTIONS_HEADER = ":books: Palabras Disponibles en el glosario IDEA :rainbow: \n\n";

    private static final String NO_DEFINITIONS = """
            Lo siento, no encontré definiciones en el glosario de IDEA relacionadas con esa palabra :pleading_face:.

            :nerd_face: Para ver las palabras disponibles en el glosario de IDEA mencióname con la palabra opciones
            """;

    private static final String DEFINITIONS_HEADER = "He encontrado las siguientes palabras, por favor selecciona una para leer su definicion: ";

    private static final String DEFINITION_HEADER = ":books: La definición de la palabra es la siguiente: \n\n";

    private static final String RESOURCE_HEADER = ":link: La fuente de la definición es la siguiente: ";

    private static final String MULTIPLE_OPTIONS_HEADER = ":thinking_face: Encontre estas palabras relacionadas con: %s \n\n";

    private static final String MULTIPLE_OPTIONS_FOOTER = "Para seleccionar las palabras por favor se mas especifico, limita la búsqueda :nerd_face:";

    private static final String NOT_ANALYTICS_FOUNDED = "Este mes no se han buscado palabras :cry:";

    private static final String ANALYTICS_HEADER = ":bar_chart: Palabras buscadas este mes: \n";

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

    public static String getMultipleOptionsHeader() {
        return MULTIPLE_OPTIONS_HEADER;
    }

    public static String getMultipleOptionsFooter() {
        return MULTIPLE_OPTIONS_FOOTER;
    }

    public static String getNotAnalyticsFounded(){ return NOT_ANALYTICS_FOUNDED;}

    public static String getAnalyticsHeader(){ return ANALYTICS_HEADER;}
}
