package devTools;

import static core.Utils.randomIntInRange;

public class DevTools {
    public static void main(String[] args) {
        generateNucleotideSequence(14);
    }

    private static void generateNucleotideSequence(int length) {
        for (int i = 0; i < length; i++) {
            char next = switch (randomIntInRange(1, 4)) {
                case 1 -> 'A';
                case 2 -> 'T';
                case 3 -> 'G';
                default -> 'C';
            };
            System.out.print(next);
        }
        System.out.println();
    }

    // Failed attempt at doing something advanced, may revisit later
//    public static void main(String[] args) {
//        Scanner scanner;
//
//        System.out.println("Choose a dev tool to run:");
//        Method[] tools = DevTools.class.getDeclaredMethods();
//        for (int i = 0; i < tools.length; i++) {
//            String name = tools[i].getName();
//            if (!name.equals("main")) {
//                System.out.println(i + ": " + name);
//            }
//        }
//        System.out.println("Enter the number of a tool to select it, or -1 to exit.");
//        System.out.println();
//
//        scanner = new Scanner(System.in);
//        try {
//            int toolNumber = scanner.nextInt();
//            if (toolNumber == -1) {
//                return;
//            }
//            Method tool = tools[toolNumber];
//            Parameter[] params = tool.getParameters();
//            String[] arguments = new String[params.length];
//            for (int i = 0; i < params.length; i++) {
//                System.out.println(params[i].getName() + ": ");
//                arguments[i] = scanner.next();
//            }
//
//            tool.invoke(null, arguments);
//        }
//        catch (InputMismatchException ime) {
//            System.err.println("Do you understand what \"Enter the NUMBER\" means?");
//        }
//        catch (ArrayIndexOutOfBoundsException aioobe) {
//            System.err.println("Number provided does not correspond to an existing tool.");
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            scanner.close();
//        }
//    }
//
//    private static void generateNucleotideSequence(String length) {
//        int lengthInt;
//        try {
//            lengthInt = Integer.parseInt(length);
//            for (int i = 0; i < lengthInt; i++) {
//                char next = switch (randomIntInRange(1, 4)) {
//                    case 1 -> 'A';
//                    case 2 -> 'T';
//                    case 3 -> 'G';
//                    default -> 'C';
//                };
//                System.out.print(next);
//            }
//            System.out.println();
//        }
//        catch (NumberFormatException nfe) {
//            System.err.println("length must be an integer.");
//        }
//    }
}
