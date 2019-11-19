package fiuba.algo3.AlgoChess;
import fiuba.algo3.AlgoChess.excepciones.CuranderoCuraHastaLaMaximaVidaExcepcion;
import org.junit.Assert;
import org.junit.Test;

public class SoldadoTest {

	@Test
	public void soldadoRecuperadoSeLeSumaVidaTest()
			throws CuranderoCuraHastaLaMaximaVidaExcepcion {
		Soldado soldado = new Soldado("aliado", 1, 2);
		soldado.reponerVida(15);

		Assert.assertEquals(100, soldado.getVida());
	}

	@Test
	public void soldadoAtacadoPierdeVidaTest() {
		Soldado soldado = new Soldado("aliado", 1, 2);

		soldado.recibirDanio(50);

		Assert.assertEquals(50, soldado.getVida());
	}

	@Test
	public void soldadoNoTieneEnemigosCercaTest() {
		Soldado soldado = new Soldado("aliado", 3, 5);

		Assert.assertFalse(soldado.enemigosCerca());
	}

	@Test
	public void soldadoNoTieneAliadosCercaTest() {
		Casillero casillero = new Casillero(6,7);
		Soldado soldado = new Soldado("enemigo", casillero);
		
		Assert.assertEquals(false, soldado.aliadosCerca());
	}


	@Test
	public void soldadoAtacaACuranderoYLeRestaVida(){
		Soldado soldado = new Soldado("enemigo", 6, 7);
		//Curandero curandero = new Curandero("enemigo", );
		//soldado.atacarEnemigo();
	}
}