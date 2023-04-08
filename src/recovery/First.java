package recovery;

import parser.*;

public class First { //implementa os conjuntos first p/ alguns n.terminais

    static public final RecoverySet inicio = new RecoverySet();
    static public final RecoverySet tipoRetorno = new RecoverySet();
    static public final RecoverySet declaracaoDeVariavel = new RecoverySet();
    static public final RecoverySet galaxia = new RecoverySet();
    static public final RecoverySet identificador = new RecoverySet();
    static public final RecoverySet abreParenteses = new RecoverySet();
    static public final RecoverySet fechaParenteses = new RecoverySet();
    static public final RecoverySet inicioFimBloco = new RecoverySet();
    static public final RecoverySet pulaLinha = new RecoverySet();
    static public final RecoverySet bloco = new RecoverySet();
    static public final RecoverySet retorno = new RecoverySet();
    static public final RecoverySet valorPrimitivo = new RecoverySet();
    static public final RecoverySet lambda = new RecoverySet();
    static public final RecoverySet constantes = new RecoverySet();
    static public final RecoverySet bigBang = new RecoverySet();
    
    
    static {
    	
    	inicio.add(new Integer(SpaceConstants.EOF));
    	
    	tipoRetorno.add(new Integer(SpaceConstants.GALAXIA));
    	tipoRetorno.add(new Integer(SpaceConstants.TIPO_VACUO));
    	tipoRetorno.add(new Integer(SpaceConstants.TIPO_QUASAR));
    	tipoRetorno.add(new Integer(SpaceConstants.TIPO_LUA));
    	tipoRetorno.add(new Integer(SpaceConstants.TIPO_ESTRELA));
    	
    	galaxia.add(new Integer(SpaceConstants.ID));
    	
    	identificador.add(new Integer(SpaceConstants.ABRE_PARENTESES));
    	identificador.add(new Integer(SpaceConstants.VIRGULA));
    	identificador.add(new Integer(SpaceConstants.IN));
    	identificador.add(new Integer(SpaceConstants.IGUAL));
    	identificador.add(new Integer(SpaceConstants.DELIMITA_ESTRELA));
    	identificador.add(new Integer(SpaceConstants.PULA_LINHA));
    	
    	abreParenteses.add(new Integer(SpaceConstants.FECHA_PARENTESES));
    	abreParenteses.add(new Integer(SpaceConstants.ID));
    	abreParenteses.add(new Integer(SpaceConstants.INICIA_VARIAVEL));
    	abreParenteses.add(new Integer(SpaceConstants.NUMERO));
    	
    	fechaParenteses.add(new Integer(SpaceConstants.INICIO_FIM_BLOCO));
    	abreParenteses.add(new Integer(SpaceConstants.PULA_LINHA));
    	
    	inicioFimBloco.add(new Integer(SpaceConstants.PULA_LINHA));
    	inicioFimBloco.add(new Integer(SpaceConstants.INICIA_VARIAVEL));
    	inicioFimBloco.add(new Integer(SpaceConstants.ANA_MARROM));

    	pulaLinha.add(new Integer(SpaceConstants.INICIA_VARIAVEL));

    	bloco.add(new Integer(SpaceConstants.RETORNO));
    	bloco.add(new Integer(SpaceConstants.INICIO_FIM_BLOCO));
    	bloco.add(new Integer(SpaceConstants.INICIA_VARIAVEL));
    	
    	retorno.add(new Integer(SpaceConstants.NUMERO));

    	valorPrimitivo.add(new Integer(SpaceConstants.INICIO_FIM_BLOCO));
    	valorPrimitivo.add(new Integer(SpaceConstants.MAIS));
    	valorPrimitivo.add(new Integer(SpaceConstants.PULA_LINHA));

    	
    	lambda.add(new Integer(SpaceConstants.INICIO_FIM_BLOCO));    	

    	constantes.add(new Integer(SpaceConstants.INICIO_FIM_BLOCO));    	
    	constantes.add(new Integer(SpaceConstants.INICIA_VARIAVEL));    	

    	bigBang.add(new Integer(SpaceConstants.INICIO_FIM_BLOCO));    	
    	
    	declaracaoDeVariavel.add(new Integer(SpaceConstants.INICIA_VARIAVEL));
    	declaracaoDeVariavel.add(new Integer(SpaceConstants.PULA_LINHA));
    	declaracaoDeVariavel.add(new Integer(SpaceConstants.IGUAL));
    	
    	
    	

    }
}
