package sincronizado;

/**
 *
 * @author leovi
 */
public class EjecutaInvocador 
{
    public static void main(String[] args) {
        Llamame llama = new Llamame();
        new Invocador("Hola", 2, llama);
        new Invocador("Muchachos", 3, llama);
        new Invocador("Sincronizado", 5, llama);
    }
}
