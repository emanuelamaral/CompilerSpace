package recovery;

import parser.SpaceConstants;

public class Follow { //implementa os conjuntos first p/ alguns n.terminais

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
    static public final RecoverySet bigBang = lambda;
    static public final RecoverySet igual = new RecoverySet();
    static public final RecoverySet numero = new RecoverySet();
    static public final RecoverySet gerarEstrela = new RecoverySet();
    static public final RecoverySet comandos = new RecoverySet();
    static public final RecoverySet iniciaVariavel = new RecoverySet();
    static public final RecoverySet tipoVariavel = new RecoverySet();
    static public final RecoverySet modoVariavel = new RecoverySet();
    static public final RecoverySet atribuicao = galaxia;
    static public final RecoverySet anaBranca = new RecoverySet();
    static public final RecoverySet condicao = new RecoverySet();
    static public final RecoverySet decideFinal = lambda;
    static public final RecoverySet decideAnaMarrom = lambda;
    static public final RecoverySet orbita = anaBranca;
    static public final RecoverySet in = new RecoverySet();
    static public final RecoverySet espaco = anaBranca;
    static public final RecoverySet configuraEspaco = new RecoverySet();
    static public final RecoverySet translacao = anaBranca;
    static public final RecoverySet buracoNegro = anaBranca;
    static public final RecoverySet virgula = new RecoverySet();
    static public final RecoverySet expressao = new RecoverySet();
    static public final RecoverySet buracoBranco = anaBranca;
    static public final RecoverySet tipoAtribuicao = retorno;
    static public final RecoverySet modoDeAtribuicao = new RecoverySet();
    static public final RecoverySet operacaoAritmetica = tipoAtribuicao;
    static public final RecoverySet and = tipoAtribuicao;
    static public final RecoverySet or = tipoAtribuicao;
    static public final RecoverySet delimitaEstrela = new RecoverySet();



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
    	identificador.add(new Integer(SpaceConstants.MAIS_IGUAL));
    	identificador.add(new Integer(SpaceConstants.MENOS_IGUAL));


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
    	bloco.union(comandos);

    	retorno.add(new Integer(SpaceConstants.NUMERO));
    	retorno.add(new Integer(SpaceConstants.DELIMITA_ESTRELA));
    	retorno.add(new Integer(SpaceConstants.ID));
    	retorno.add(new Integer(SpaceConstants.LUA_CHEIA));
    	retorno.add(new Integer(SpaceConstants.LUA_NOVA));
    	retorno.add(new Integer(SpaceConstants.TIPO_VACUO));


    	valorPrimitivo.add(new Integer(SpaceConstants.INICIO_FIM_BLOCO));
    	valorPrimitivo.add(new Integer(SpaceConstants.MAIS));
    	valorPrimitivo.add(new Integer(SpaceConstants.PULA_LINHA));


    	lambda.add(new Integer(SpaceConstants.INICIO_FIM_BLOCO));

    	constantes.add(new Integer(SpaceConstants.INICIO_FIM_BLOCO));
    	constantes.add(new Integer(SpaceConstants.INICIA_VARIAVEL));

    	declaracaoDeVariavel.add(new Integer(SpaceConstants.INICIA_VARIAVEL));
    	declaracaoDeVariavel.add(new Integer(SpaceConstants.PULA_LINHA));
    	declaracaoDeVariavel.add(new Integer(SpaceConstants.IGUAL));

    	igual.add(new Integer(SpaceConstants.NUMERO));
    	igual.add(new Integer(SpaceConstants.DELIMITA_ESTRELA));

    	numero.add(new Integer(SpaceConstants.PULA_LINHA));
    	numero.add(new Integer(SpaceConstants.VIRGULA));
    	numero.add(new Integer(SpaceConstants.FECHA_PARENTESES));
    	numero.add(new Integer(SpaceConstants.FECHA_PARENTESES));
    	numero.add(new Integer(SpaceConstants.DELIMITA_ESTRELA));

    	gerarEstrela.add(new Integer(SpaceConstants.PULA_LINHA));
    	gerarEstrela.add(new Integer(SpaceConstants.DELIMITA_ESTRELA));

    	comandos.add(new Integer(SpaceConstants.PULA_LINHA));
    	comandos.add(new Integer(SpaceConstants.ID));
    	comandos.add(new Integer(SpaceConstants.ANA_BRANCA));
    	comandos.add(new Integer(SpaceConstants.ORBITA));
    	comandos.add(new Integer(SpaceConstants.TRANSLACAO));
    	comandos.add(new Integer(SpaceConstants.BURACO_NEGRO));
    	comandos.add(new Integer(SpaceConstants.BURACO_BRANCO));

    	iniciaVariavel.add(new Integer(SpaceConstants.TIPO_QUASAR));
    	iniciaVariavel.add(new Integer(SpaceConstants.TIPO_ESTRELA));
    	iniciaVariavel.add(new Integer(SpaceConstants.TIPO_LUA));

    	tipoVariavel.add(new Integer(SpaceConstants.ID));
    	tipoVariavel.add(new Integer(SpaceConstants.VIRGULA));

    	modoVariavel.add(new Integer(SpaceConstants.VIRGULA));

    	anaBranca.add(new Integer(SpaceConstants.ABRE_PARENTESES));

    	condicao.add(new Integer(SpaceConstants.AND));
    	condicao.add(new Integer(SpaceConstants.OR));
    	condicao.add(new Integer(SpaceConstants.FECHA_PARENTESES));

    	in.add(new Integer(SpaceConstants.ESPACO));
    	in.add(new Integer(SpaceConstants.ID));
    	in.union(igual);

    	configuraEspaco.add(new Integer(SpaceConstants.FECHA_PARENTESES));

    	virgula.add(new Integer(SpaceConstants.ID));
    	virgula.union(igual);

    	expressao.add(new Integer(SpaceConstants.FECHA_PARENTESES));
    	expressao.add(new Integer(SpaceConstants.MAIOR));
    	expressao.add(new Integer(SpaceConstants.MENOR));
    	expressao.add(new Integer(SpaceConstants.MAIOR_IGUAL));
    	expressao.add(new Integer(SpaceConstants.IN));


    	modoDeAtribuicao.add(new Integer(SpaceConstants.PULA_LINHA));
    	modoDeAtribuicao.add(new Integer(SpaceConstants.MAIS));
    	modoDeAtribuicao.add(new Integer(SpaceConstants.MENOS));
    	modoDeAtribuicao.add(new Integer(SpaceConstants.VEZES));
    	modoDeAtribuicao.add(new Integer(SpaceConstants.DIVIDE));
    	modoDeAtribuicao.add(new Integer(SpaceConstants.ABRE_PARENTESES));
    	modoDeAtribuicao.add(new Integer(SpaceConstants.FECHA_PARENTESES));

    	delimitaEstrela.add(new Integer(SpaceConstants.NUMERO));
    	delimitaEstrela.add(new Integer(SpaceConstants.ID));
    	delimitaEstrela.add(new Integer(SpaceConstants.DELIMITA_ESTRELA));

    }
}
