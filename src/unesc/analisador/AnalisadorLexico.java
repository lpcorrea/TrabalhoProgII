package unesc.analisador;


import java.util.Stack;

public class AnalisadorLexico {
    
    public Stack<String> gerarTokens(String programa){
        //Criação das Pilhas        
        Stack<String> pilhaTokens = new Stack<>();
        Stack<String> pilhaAux = new Stack<>();
             
        int a = -1;
        /*Vetor "tokens" receberá oque estiver sendo passado 
        por parametro na varivel "texto". */
         
        //Vetor "tokens", cereberá o conteudo quebro por espaços em branco        
        String[] tokens = programa.split(" ");
        //Remove os espaços em branco e Verifica se "s" está vazio.
        //Joga o texto formatado para a pilha Auxiliar.
        for(String s : tokens) {
            if(!s.trim().isEmpty()){
                pilhaAux.push(s);
                a++;
            }
        }
        
        //Joga a pilha invertida para a pilha"pilhaIni"        
        while(!pilhaAux.isEmpty()){
            for(int i=0;i<=a;i++){
                pilhaTokens.push(pilhaAux.pop());
                      
            }
            
        }
        return pilhaTokens;
    }
    
}
