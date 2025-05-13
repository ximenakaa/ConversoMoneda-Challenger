package com.aluracursos;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Scanner;


public class Principal {
    public static void main(String[] args) {

        Map<Integer, String> monedas = Map.of(
                1, "CLP",
                2, "USD",
                3, "ARS",
                4, "BRL",
                5, "RUB",
                6, "EUR",
                7, "CAD"
        );

        Map<String, String> nombresMonedas = Map.of(
                "CLP", "Pesos chilenos",
                "USD", "Dólares estadounidenses",
                "ARS", "Pesos argentinos",
                "BRL", "Reales brasileños",
                "RUB", "Rublos rusos",
                "EUR", "Euros",
                "CAD", "Dólares canadienses"
        );

        ConsultaMoneda conversor = new ConsultaMoneda();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("╔═════════════════════╗");
            System.out.println("║  Conversor-Monedas  ║");
            System.out.println("╚═════════════════════╝");
            System.out.println("""
                    1. Realizar conversión
                    2. Salir
                    """);

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                System.out.print("Ingrese el monto a convertir: ");
                double monto = scanner.nextDouble();

                mostrarMenuMonedas();

                System.out.print("Ingrese número de moneda de origen: ");
                String codeBase = monedas.get(scanner.nextInt());


                System.out.print("Ingrese número de moneda a convertir: ");
                String codeTo = monedas.get(scanner.nextInt());

                if (codeBase == null || codeTo == null) {
                    System.out.println("Moneda no válida. Intente nuevamente.");
                    continue;
                }

                try {
                    JsonObject rates = conversor.getRates(codeBase);
                    double rate = rates.get(codeTo).getAsDouble();
                    double resultado = monto * rate;

                    System.out.printf("Resultado de la conversión es: %.2f %s = %.2f %s\n",
                            monto,
                            nombresMonedas.get(codeBase),  //  nombre completo de codeBase
                            resultado,
                            nombresMonedas.get(codeTo));   //  nombre completo de codeTo

                } catch (Exception e) {
                    System.err.println("Error al obtener los tipos de cambio: " + e.getMessage());

                }

            } else if (opcion == 2) {
                System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta luego!");
                break;
            } else {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
    static void mostrarMenuMonedas() {
        System.out.println("""
                --------Monedas-------
                1 - CLP (Peso Chileno)
                2 - USD (Dólar EE.UU.)
                3 - ARS (Peso Argentino)
                4 - BRL (Real Brasileño)
                5 - RUB (Rublo Ruso)
                6 - EUR (Euro)
                7 - CAD (Dólar Canadiense)
                """);
    }
}
