package lanchonetedulafa;

public class Exemplo {

    public static int contador;

    public Exemplo() {
        contador = contador + 1;
    }

    public static void main(String[] args) {
        Exemplo e1 = new Exemplo();
        Exemplo e2 = new Exemplo();

        System.out.println(e1.contador);
        System.out.println(e2.contador);
        System.out.println(Exemplo.contador);
    }
}