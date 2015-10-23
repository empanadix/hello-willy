public List<Software> loadDOM(File file){
	List<Software> toReturn = new ArrayList<>();
	Document document;

	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	try {
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = db.parse(file);

		Element element = document.getDocumentElement();
		NodeList nodeList;
		nodeList = element.getElementsByTagName("nombre");
		for (int i = 0; i < nodeList.getLength(); i++) {
			List<String> rolev = new ArrayList<String>();
			String nombre="";
			String descripcion = "";
			String version = "";
			Double precio = 0.0;
			String requisitos = "";
			
			
			nombre = getTextValue(nombre, element, "nombre",i);
			if (nombre != null) {
				if (!nombre.isEmpty())
					rolev.add(nombre);
			}
			descripcion = getTextValue(descripcion, element, "descripcion",i);
			if (descripcion != null) {
				if (!descripcion.isEmpty())
					rolev.add(descripcion);
			}
			version = getTextValue(version, element, "version",i);
			if (version != null) {
				if (!version.isEmpty())
					rolev.add(version);
			}
			String precio2 = getTextValue(String.valueOf(precio), element, "precio",i);
			if ( precio2 != null) {
				if (!precio2.isEmpty())
					rolev.add(precio2);
			}
			requisitos = getTextValue(requisitos, element, "requisitos",i);
			if ( requisitos != null) {
				if (!requisitos.isEmpty())
					rolev.add(requisitos);
		}
					
		toReturn.add(new Software(rolev.get(0),rolev.get(1),rolev.get(2),
								Double.valueOf(rolev.get(3)),rolev.get(4)));
		
	}

	} catch (ParserConfigurationException pce) {
		System.out.println(pce.getMessage());
	} catch (SAXException se) {
		System.out.println(se.getMessage());
	} catch (IOException ioe) {
		System.err.println(ioe.getMessage());
	}

	return toReturn;
}

public void saveDOM(List<Software> lSw,File act){

	try {
		DocumentBuilderFactory fábricaCreadorDocumento = DocumentBuilderFactory.newInstance();
		DocumentBuilder creadorDocumento = fábricaCreadorDocumento.newDocumentBuilder();
		//Crear un nuevo documento XML
		Document documento = creadorDocumento.newDocument();

		//Crear el nodo raíz y colgarlo del documento
		Element elementoRaiz = documento.createElement("softwares");
		documento.appendChild(elementoRaiz);

		
		for (int i = 0; i < lSw.size(); i++) {  
			Element elementoSotware = documento.createElement("software");
			elementoRaiz.appendChild(elementoSotware);
			
			Element elementoNombre = documento.createElement("nombre"); 
			elementoSotware.appendChild(elementoNombre);  
			Text textoNombre = documento.createTextNode(lSw.get(i).getNombre());
			elementoNombre.appendChild(textoNombre); 
			
			Element elementoDescripcion = documento.createElement("descripcion");
			elementoSotware.appendChild(elementoDescripcion);
			Text textoDescripcion = documento.createTextNode(lSw.get(i).getDescripcion());
			elementoDescripcion.appendChild(textoDescripcion); 
			
			Element elementoVersion = documento.createElement("version");         
			elementoSotware.appendChild(elementoVersion);
			Text textoVersion = documento.createTextNode(lSw.get(i).getVersion());
			elementoVersion.appendChild(textoVersion); 
			
			Element elementoPrecio = documento.createElement("precio");
			elementoSotware.appendChild(elementoPrecio);
			Text textoPrecio = documento.createTextNode(String.valueOf(lSw.get(i).getPrecio()));
			elementoPrecio.appendChild(textoPrecio); 
			
			Element elementoRequisitos = documento.createElement("requisitos");
			elementoSotware.appendChild(elementoRequisitos);
			Text textoRequisitos = documento.createTextNode(lSw.get(i).getRequisitos());
			elementoRequisitos.appendChild(textoRequisitos); 
		}

		//Generar el tranformador para obtener el documento XML en un fichero
		TransformerFactory fábricaTransformador = TransformerFactory.newInstance();
		Transformer transformador = fábricaTransformador.newTransformer();
		//Insertar saltos de línea al final de cada línea
		transformador.setOutputProperty(OutputKeys.INDENT, "yes");
		//Añadir 3 espacios delante, en función del nivel de cada nodo
		transformador.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "3");
		Source origen = new DOMSource(documento);
		Result destino = new StreamResult(act);
		transformador.transform(origen, destino);

	} catch (ParserConfigurationException ex) {
		System.out.println("ERROR: No se ha podido crear el generador de documentos XML\n"+ex.getMessage());
		ex.printStackTrace();
	} catch (TransformerConfigurationException ex) {
		System.out.println("ERROR: No se ha podido crear el transformador del documento XML\n"+ex.getMessage());
		ex.printStackTrace();
	} catch (TransformerException ex) {
		System.out.println("ERROR: No se ha podido crear la salida del documento XML\n"+ex.getMessage());
		ex.printStackTrace();
	}
}