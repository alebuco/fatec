/**
 *
 * @author Alexandre Ciaco Nunes
 */
public class Navio {
	int tipo;
	int tamanho;
	boolean horizontal;
	int posicao[][];
	int jogador;

	
	/* 
	 * Cria o navio com os dados de posicionamento
	 * inseridos na matriz de posicao dentro do 
	 * objeto Navio
	 * 
	 * posicao x
	 * posicao y
	 * direcao horizontal/vertical
	 * tamanho
	 * tipo de navio ( 	1 - porta avioes
	 * 					2 - encouracado
	 * 					3 - cruzador
	 * 					4 - submarino
	 * 				)
	 */
	void criaNavio(Navio n){
		for (int i = 1 ; i < n.tamanho; i++) {
			if (n.horizontal){
				n.posicao[i][0]= n.posicao[i-1][0]+1; 
				n.posicao[i][1] = n.posicao[0][1];
			} else {
				n.posicao[i][1]= n.posicao[i-1][1]+1;
				n.posicao[i][0] = n.posicao[0][0];
			}
		}
	}
}
