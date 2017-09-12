import java.util.Arrays;
/**
*
* @author Alexandre Ciaco Nunes
*/
public class Tabuleiro {
	int id;
	int tamanho;
	int posicao[][];

	/*
	 * Cria um tabuleiro com as dimensoes (tamanho x tamanho) dentro da matriz
	 * posicao[tamanho][tamanho]
	 * 
	 */
	void criarTabuleiro(Tabuleiro t) {
		for (int i = 0; i < posicao.length; i++) {
			Arrays.fill(t.posicao[i], 0);
		}
	}

	/*
	 * Posiciona o navio criado dentro do tabuleiro do jogador
	 * 
	 */
	void posicionaNoTabuleiro(Navio n, Tabuleiro t) {
		for (int i = 0; i < n.tamanho; i++) {
			t.posicao[n.posicao[i][0]][n.posicao[i][1]] = 1;
		}

	}

	static void mostraTabuleiro(Tabuleiro t1, Tabuleiro t2) {
		System.out.println("+---+---+---+---+---+---+---+---+---+---+    +---+---+---+---+---+---+---+---+---+---+");
		System.out.println("|                Jogador                |    |               Computador              |");
		for (int i = 0; i < t1.tamanho; i++) {
			System.out.println("|---+---+---+---+---+---+---+---+---+---|    |---+---+---+---+---+---+---+---+---+---|");
			for (int j = 0; j < t1.tamanho; j++) {
				if (t1.posicao[j][i] > 0 && t1.posicao[j][i] < 5) {
					System.out.print("| # ");
				}
				if (t1.posicao[j][i] == 9) {
					System.out.print("| * ");
				}
				if (t1.posicao[j][i] == 8) {
					System.out.print("| ~ ");
				}
				if (t1.posicao[j][i] == 0) {
					System.out.print("|   ");
				}
			}
			System.out.print("|    ");
			for (int j = 0; j < t2.tamanho; j++) {
				if (t2.posicao[j][i] > 0 && t2.posicao[j][i] < 5) {
					System.out.print("|   ");
				}
				if (t2.posicao[j][i] == 9) {
					System.out.print("| * ");
				}
				if (t2.posicao[j][i] == 8) {
					System.out.print("| ~ ");
				}
				if (t2.posicao[j][i] == 0) {
					System.out.print("|   ");
				}				
			}
			System.out.println("|");
		}
		System.out.println("+---+---+---+---+---+---+---+---+---+---|    |---+---+---+---+---+---+---+---+---+---+");
	}

}
