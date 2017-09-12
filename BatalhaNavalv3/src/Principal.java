import java.util.Random;
import java.util.Scanner;

/**
*
* @author Alexandre Ciaco Nunes
*/

public class Principal {

	static Principal bn = new Principal();
	static Scanner sc = new Scanner(System.in);
	static Random rd = new Random();

	public static void main(String[] args) {
		System.out.println("Tamanho da lateral do tabuleiro: ");
		int tamanho = Integer.parseInt(sc.next());

		/*
		 * Instancia o tabuleiro 1 para o Jogador
		 */
		Tabuleiro t1 = new Tabuleiro();
		t1.id = 0;
		t1.tamanho = tamanho;
		t1.posicao = new int[tamanho][tamanho];
		t1.criarTabuleiro(t1);

		/*
		 * Instancia o tabuleiro 2 para o Computador
		 */
		Tabuleiro t2 = new Tabuleiro();
		t2.id = 1;
		t2.tamanho = tamanho;
		t2.posicao = new int[tamanho][tamanho];
		t2.criarTabuleiro(t2);

		/*
		 * Cria os tamanhos dos navios conforme tipo:
		 */ 
		//      	  Tipo Tamanho
		//porta avioes 	1      	5 
		//encouracado 	2 		4 
		//cruzador 		3	 	3 
		//submarino 	4 		1
		
		int tipo[] = new int[] { 1, 2, 3, 4, 4 };
		int tam_tipo[] = new int[] { 5, 4, 3, 1, 1 };

		/*
		 * Formacao das esquadras dos jogadores 1 e 2(computador)
		 * 
		 */
		Navio esquadra[][] = new Navio[5][2];
		Navio navio = new Navio();
		for (int i = 1; i < 3; i++) {
			Tabuleiro t;
			if (i == 1) {
				t = t1;
			} else {
				t = t2;
			}

			for (int j = 0; j < 5; j++) {
				navio.tipo = tipo[j];
				navio.tamanho = tam_tipo[j];
				esquadra[j][i - 1] = navio;
				esquadra[j][i - 1] = bn.insereNavio(esquadra[j][i - 1], t, i);
			}

		}

		/*
		 * Jogando ...
		 * 
		 */
		int acertosj = 14, acertosc = 14, x, y;
		while (acertosc > 0 && acertosj > 0) {
			System.out.println("Entre com a coordenada de tiro x:");
			x = Integer.parseInt(sc.next());
			System.out.println("Entre com a coordenada de tiro x:");
			y = Integer.parseInt(sc.next());
			if (bn.atirar(x, y, t2)) {
				acertosj--;
			}
			x = rd.nextInt(tamanho);
			y = rd.nextInt(tamanho);
			System.out.println("Entre com a coordenada de tiro x:" + x);
			System.out.println("Entre com a coordenada de tiro y:" + y);
			if (bn.atirar(x, y, t1)) {
				acertosc--;
			}
			Tabuleiro.mostraTabuleiro(t1,t2);
		}
		if (acertosc == 0) {
			System.out.println("\nComputador venceu.");
		} else {
			System.out.println("\nJogador venceu.");
		}

	}

	/*
	 * Cadastramento da posicao dos navios conforme o tamanho, tipo e jogador
	 * 
	 * Também faz o posicionamento do navio dentro do tabuleiro para o jagador
	 * determindado
	 * 
	 */
	Navio insereNavio(Navio navio, Tabuleiro t, int jogador) {
		navio.posicao = new int[navio.tamanho][2];
		do {
			if (jogador == 1) {
				navio.jogador=1;
				System.out.println("posicao x:");
				navio.posicao[0][0] = Integer.parseInt(sc.next());
				System.out.println("posicao y:");
				navio.posicao[0][1] = Integer.parseInt(sc.next());
				System.out.println("Direcao Horizontal? (S/N)");
				navio.horizontal = "S".equals(sc.next());
				navio.criaNavio(navio);
			} else {
				navio.jogador=2;
				navio.posicao[0][0] = rd.nextInt(t.tamanho);
				// System.out.println("posicao x:" + navio.posicao[0][0]);
				navio.posicao[0][1] = rd.nextInt(t.tamanho);
				//System.out.println("posicao x:" + navio.posicao[0][1]);
				// System.out.println("Direcao Horizontal? (S/N)");
				navio.horizontal = "1".equals(rd.nextInt(2));
				navio.criaNavio(navio);
			}

		} while (!bn.verificaPosicao(navio, t));

		t.posicionaNoTabuleiro(navio, t);

		return navio;
	}

	/*
	 * Verifica se a posicao escolhida para posicionar o navio eh valida :
	 * 
	 * 1- se esta dentro dos limites do tabuleiro;
	 * 
	 * 2 - se ja esta ocupada por outro navio
	 * 
	 */
	boolean verificaPosicao(Navio n, Tabuleiro t) {
		int x = n.posicao[0][0], y = n.posicao[0][1];
		if (x < 0 || x + n.tamanho - 1 >= t.tamanho || y < 0 || y + n.tamanho - 1 >= t.tamanho) {
			if (n.jogador==1){
				System.out.println("Coordenadas fora do tabuleiro. Redigite.");
			}
			return false;
		} else {
			for (int i = 0; i < n.tamanho; i++) {
				x = n.posicao[i][0];
				y = n.posicao[i][1];
				if (t.posicao[x][y] != 0) {
					if (n.jogador==1){
						System.out.println("Posicao ja ocupada. Redigite.");
					}
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Verifica se acertou o tiro
	 * 
	 */
	boolean atirar(int x, int y, Tabuleiro t) {
		if (x < 0 || x >= t.tamanho || y < 0 || y >= t.tamanho) {
			System.out.println("Coordenadas fora do tabuleiro. Redigite.");
			return false;
		}
		if (t.posicao[x][y] > 0 && t.posicao[x][y] < 5) {
			System.out.println("Acertou !!!");
			t.posicao[x][y] = 9;
			return true;
		}
		if (t.posicao[x][y] == 9 ) {
			return false;
		}
		t.posicao[x][y] = 8;
		System.out.println("Agua ...");
		return false;
	}

}
