package unesc.analisador;

import unesc.gramatica.Gramatica;
import java.util.Map;
import java.util.Stack;

public class AnalisadorSintatico {

    private final Map<String, Integer> linguagem = Gramatica.LINGUAGEM;
    private final Map<String, String> gramatica = Gramatica.GRAMATICA;

    public int validaA(Integer a, String pa){

        //Verifica se "a" é vazio
        if(a == null){

            // Trata as exceções           
            try {

                //"Converte" uma String em um tipo primitivo. 
                //Executa o código abaixo, se ocorrer algum erro executa o "CATCH"             
                Integer.valueOf(pa);
                a = 26;
               
            }catch(Exception e){
                a = 25;
            }            
        }        
        return a;
    }
    
    public void analisar(Stack<String> tokens) {
        // Cria as pilhas.
        Stack<String> pilhaA = new Stack<>();
        Stack<Integer> pilhaX = new Stack<>();

        // Inicializa as pilhas.
        pilhaA = tokens;
        pilhaX.add(52);

        // Vai executar o bloco de informações até que ambas as pilhas fiquem vazias.
        while (!pilhaA.isEmpty() && !pilhaX.isEmpty()) {
            // Coloca o valor no topo da pilha.
            String pa = pilhaA.peek();
            Integer x = pilhaX.peek();

            // "a" vai receber a "Chave"(Inteiro) que representa os TOKENS .
            Integer a = Gramatica.LINGUAGEM.get(pa);

            // "a" vai receber a tratativa da função "validaA".
            a = validaA(a, pa);

            // Verifica se "x" é menor que 52(TERMINAL)
            if (x < 52) {
                // Compara os valores das pilhas.
                if (a.equals(x)) {
                    // Remove do topo da pilha.
                    pa = pilhaA.pop();
                    x = pilhaX.pop();
                } else {
                    // Se os elementos das pilhas forem diferentes retorna a mensagem,
                    // com os elementos divergentes.
                    System.out.println("[Erro 001:" + "Elemento PA [" + pa + "]" + "Elemento PX [" + x + "]");
                    break;
                }

            } else {

                // Se "x" for maior que 52(NÃO TERMINAL).
                // "derivacao" recebe uma String equivalente a combinação de valores,
                // entre "x" e "a".
                String derivacao = Gramatica.GRAMATICA.get(x + "," + a);

                // Remove do topo da pilha.
                x = pilhaX.pop();

                // Se "derivacao" não receber VAZIO executa
                if (derivacao != null) {

                    // Vetor "y1y2" recebe valores inteiros quebrados por "|",
                    // equivalentes a String recebida pela "derivacao".
                    Integer[] y1y2 = Gramatica.geraDadosCruzamentoTabParsingToken(derivacao);

                    // Percorre o vetor(DESEMPILHANDO), e colocando na pilha os inteiros recebidos.
                    for (int i = y1y2.length - 1; i >= 0; i--) {
                        pilhaX.push(y1y2[i]);
                    }
                }
            }
        }
        System.out.println("PROJETO COMPILADO COM SUCESSO !!!");
    }

    /**
     * Este método verifica se um token é um IDENTIFICADOR ou um INTEIRO
     */
    private Integer getIdentificadorOuInteiro(String token) {
        // verifica se é um identificador ou inteiro
        char[] cList = token.toCharArray();
        boolean identificador = true;
        for (char c : cList) {
            if (Character.getType(c) != Character.UPPERCASE_LETTER) {
                identificador = true;
            }
        }

        if (identificador) {
            return linguagem.get("IDENTIFICADOR");
        }
        return linguagem.get("INTEIRO");
    }
}
