package io.apiman.gateway.platforms.servlet;

public class TokenUtils {
	
	// Chave de acesso
	private static final String strConsistencia = "ASPC";
	//private static final int posStrConsistencia = 0;
	private static final int posCdInstituicaoCliente = 1;
	private static final int posNmCache = 2;
	private static final int posUsuario = 3;
	private static final int posApiKey = 4;
		
    public static void validadeToken (String token) throws Exception {
    	descompactaChave(token);
    }
    
    public static String getNomeCache(String token) {
    	token = descompactaChave(token);
        String[] vetKey = token.split(";");
        return vetKey[posNmCache];
	}

    public static String getCodigoCliente(String token) {
    	token = descompactaChave(token);
        String[] vetKey = token.split(";");
        return vetKey[posCdInstituicaoCliente];
	}

    public static String getCodigoUsuario(String token) {
    	token = descompactaChave(token);
        String[] vetKey = token.split(";");
        return vetKey[posUsuario];
	}
    
    public static String getApiKey(String token) {
    	token = descompactaChave(token);
        String[] vetKey = token.split(";");
        return vetKey[posApiKey];
	}

    private static String descompactaChave(String token) {
        if (token != null) {
        	token = descompactaString(strConsistencia + token);
        }
        return token;
    }

    private static String descompactaString(String str) {
        String ret = null;
        
        if (str != null && str.startsWith(strConsistencia)) {
            str = str.substring(strConsistencia.length(), str.length());
            ret = (String)Base64.decodeToObject(str);
            if (ret.indexOf("&amp;") >= 0) {
            	ret = ret.replaceAll("&amp;", "&");
            }
        }
        return ret;
    }

}
