package fiuba.algo3.AlgoChess.entidades;

import java.util.ArrayList;
import java.util.List;

import fiuba.algo3.AlgoChess.Ataques.ArmaParaCuerpoACuerpo;
import fiuba.algo3.AlgoChess.direccion.Direccion;
import fiuba.algo3.AlgoChess.distancia.BuscadorDeEntidades;
import fiuba.algo3.AlgoChess.excepciones.CasilleroOcupadoExcepcion;
import fiuba.algo3.AlgoChess.excepciones.CuranderoCuraHastaLaMaximaVidaExcepcion;
import fiuba.algo3.AlgoChess.tablero.Posicion;
import fiuba.algo3.AlgoChess.tablero.Tablero;

public class Soldado implements Entidad, ArmaParaCuerpoACuerpo {

	private final int DISTANCIA_MAX_ATAQUE  = 2;
	private final int DISTANCIA_MIN_ATAQUE = 1;
	private final int DANIO_CUERPO   = 10;
	
	private final int VIDAINICIAL = 100;
	
	private Bando bando;
	
	private int vida = VIDAINICIAL;
	
	private int costo = 1;
	
	private Posicion posicion;
	
	public Tablero tablero = Tablero.getInstanciaTablero();
	
	private BuscadorDeEntidades buscador = new BuscadorDeEntidades(tablero.getMap());

	public Soldado(Bando bando, int fila, int columna) {
		this.bando = bando;
		this.posicion = new Posicion(fila, columna);
	}

	public int getVida() {
		return this.vida;
	}
	
	public boolean estaEnRango(Entidad entidad) {
		RadarDeEntidades distancia = new RadarDeEntidades(DISTANCIA_MIN_ATAQUE,DISTANCIA_MAX_ATAQUE);
		return (distancia.estaEnElRadar(this.getPosicion().calcularDistanciaCon(entidad.getPosicion().getFila(),entidad.getPosicion().getColumna())));
		
	}
	
	public List<Entidad> filtrarAtacables(List<Entidad> enemigos){
		List<Entidad> filtrados = new ArrayList<Entidad>();
		for(Entidad entidad : enemigos) {
			if(estaEnRango(entidad)) {
				filtrados.add(entidad);
			}
		}
		return filtrados;
	}

	@Override
	public void recibirDanio(int danio) {
		this.vida -= danio;
	}

	@Override
	public int getCosto() {
		return this.costo;
	}

	@Override
	public void reponerVida(int curacion) throws CuranderoCuraHastaLaMaximaVidaExcepcion{
		if ((this.vida += curacion) > VIDAINICIAL){
			this.vida = VIDAINICIAL;
		}
	}

	@Override
	public void mover(Direccion direccion) {
		this.tablero.mover(this, this.posicion, direccion.avanzar(this.posicion));
		this.posicion = direccion.avanzar(this.posicion);
	}

    @Override
    public Entidad agregar(Entidad otraEntidad) {
        throw new CasilleroOcupadoExcepcion("NO se pude realizar dicha acción");
    }

    public Posicion getPosicion() {
		return this.posicion;
	}

	@Override
	public void espada(List<Entidad> entidad, int danio) {
		for(Entidad entidadAux : entidad) {
			entidadAux.recibirDanio(danio);
		}
	}

	@Override
	public Bando getBando() {
		return this.bando;
	}

	@Override
	public void atacarEnemigo() {
		List<Entidad> enemigos = buscador.buscarEnemigos(this.bando);
		espada(filtrarAtacables(enemigos),DANIO_CUERPO);
	}

}
