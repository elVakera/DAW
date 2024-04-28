public static void afegirAlHash(Producte p){
        int unitats = 1;
        String key = p.getCodiBarres() + p.getPreu();

        if(!(HASH_CARRO_CAIXA.containsKey(key))){
            String[] valueCaixa = new String[3];
            valueCaixa[0] = p.getNom();
            valueCaixa[1] = unitats + "";
            valueCaixa[2] = p.getPreu() + "";

            //si pertenece a textil
            if(p instanceof Textil){
                for(Map.Entry<String, String> doc : DOCUMENT.entrySet()){
                    String valorDoc = doc.getValue();   //valor preu
                    String valorKey = doc.getKey();     //valor codi barres

                    //si coinciden los codigos de barras pero no el precio
                    if(valorKey.equals(p.getCodiBarres())){
                        if(!(p.getPreu() + "").equals(valorDoc)){
                            valueCaixa[2] = valorDoc;       //modifica el valor al del doc

                        }else {
                            valueCaixa[2] = p.getPreu() + "";   //modifica el valor al del preu
                        }
                    }
                }
            }

            HASH_CARRO_CAIXA.put(key, valueCaixa);

        }else {
            String[] valueHash = new String[3];
            valueHash[0] = HASH_CARRO_CAIXA.get(key)[0];
            valueHash[1] = (Integer.parseInt(HASH_CARRO_CAIXA.get(key)[1]) + unitats) + "";
            valueHash[2] = p.getPreu() + "";

            HASH_CARRO_CAIXA.replace(key, valueHash);
        }
