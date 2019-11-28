package fiuba.algo3.AlgoChess;
import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.AlgoChess.direccion.Abajo;
import fiuba.algo3.AlgoChess.direccion.Arriba;
import fiuba.algo3.AlgoChess.direccion.Derecha;
import fiuba.algo3.AlgoChess.direccion.Direccion;
import fiuba.algo3.AlgoChess.direccion.Izquierda;
import fiuba.algo3.AlgoChess.entidades.Aliado;
import fiuba.algo3.AlgoChess.entidades.Bando;
import fiuba.algo3.AlgoChess.entidades.Catapulta;
import fiuba.algo3.AlgoChess.entidades.Curandero;
import fiuba.algo3.AlgoChess.entidades.Enemigo;
import fiuba.algo3.AlgoChess.entidades.Soldado;
import fiuba.algo3.AlgoChess.excepciones.CasilleroOcupadoExcepcion;
import fiuba.algo3.AlgoChess.tablero.Posicion;
import fiuba.algo3.AlgoChess.tablero.Tablero;

public class SoldadoTest {

	@Test
	public void soldadoRecuperadoSeLeSumaVidaTest() {
		Bando bando = new Aliado();
		Soldado soldado = new Soldado(bando, 1, 2);
		soldado.reponerVida(15);

		Assert.assertEquals(100, soldado.getVida());
	}

	@Test
	public void soldadoAtacadoPierdeVidaTest() {
		Bando bando = new Aliado();
		Soldado soldado = new Soldado(bando, 1, 2);

		soldado.recibirDanio(50);

		Assert.assertEquals(50, soldado.getVida());
	}

	@Test
	public void soldadoAtacaACuranderoYLeRestaVida() {
		Bando bando1 = new Aliado();
		Bando bando2 = new Enemigo();
		Soldado soldado = new Soldado(bando2, 6, 7);
		Curandero curandero = new Curandero(bando1, 4, 9);
		soldado.atacarEnemigo();
		Assert.assertEquals(75, curandero.getVida());
	}

	@Test
	public void soldadoSeMueveParaArriba() {
		Bando bando = new Aliado();
		Soldado soldado = new Soldado(bando, 6, 7);
		Posicion posicionNueva = new Posicion(5, 7);
		Direccion direccion = new Arriba();

		Tablero tablero = Tablero.getInstanciaTablero();
		tablero.agregarEntidadEnCasillero(soldado,6, 7 );

		soldado.mover(direccion);

		assertEquals(soldado.getPosicion(), posicionNueva);
		assertEquals(soldado, tablero.getEntidadEnPosicion(posicionNueva));
	}

	@Test
	public void soldadoSeMueveParaAbajo() {
		Bando bando = new Enemigo();
		Soldado soldado = new Soldado(bando, 6, 7);
		Posicion posicionNueva = new Posicion(7, 7);
		Direccion direccion = new Abajo();

		Tablero tablero = Tablero.getInstanciaTablero();
		tablero.agregarEntidadEnCasillero(soldado,6, 7);

		soldado.mover(direccion);

		assertEquals(soldado.getPosicion(), posicionNueva);
		assertEquals(soldado, tablero.getEntidadEnPosicion(posicionNueva));
	}

	@Test
	public void soldadoSeMueveParaLaIzquierda() {
		Bando bando = new Enemigo();
		Soldado soldado = new Soldado(bando, 6, 7);
		Posicion posicionNueva = new Posicion(6, 6);

		Direccion direccion = new Izquierda();

		Tablero tablero = Tablero.getInstanciaTablero();
		tablero.agregarEntidadEnCasillero(soldado,6, 7);

		soldado.mover(direccion);

		assertEquals(soldado.getPosicion(), posicionNueva);
		assertEquals(soldado, tablero.getEntidadEnPosicion(posicionNueva));
	}

	@Test
	public void soldadoSeMueveParaLaDerecha() {
		Bando bando = new Enemigo();
		Soldado soldado = new Soldado(bando, 6, 7);
		Posicion posicionNueva = new Posicion(6, 8);

		Direccion direccion = new Derecha();

		Tablero tablero = Tablero.getInstanciaTablero();
		tablero.agregarEntidadEnCasillero(soldado,6,7 );

		soldado.mover(direccion);

		assertEquals(soldado.getPosicion(), posicionNueva);
		assertEquals(soldado, tablero.getEntidadEnPosicion(posicionNueva));
	}

	@Test
	public void soldadoNoPuedeMoverseACasilleroOcupado() {
		boolean errorAtrapado = false;
		Posicion posicionEsperada = new Posicion(5, 5);
		Bando bando1 = new Aliado();
		Bando bando2 = new Aliado();
		Soldado soldado = new Soldado(bando1, 5,5);
		Catapulta catapulta = new Catapulta(bando2, 5,4);
		Tablero tablero = Tablero.getInstanciaTablero();
		Direccion direccion = new Izquierda();

		try{
			tablero.agregarEntidadEnCasillero(soldado, 5, 5);
			tablero.agregarEntidadEnCasillero(catapulta,5,4);
			soldado.mover(direccion);
		}catch(CasilleroOcupadoExcepcion e){
			errorAtrapado = true;
		}

		Assert.assertTrue(errorAtrapado);
		Assert.assertEquals(posicionEsperada, soldado.getPosicion());
	}
}