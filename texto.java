private void chooseFileToOpen() 
		throws Exception,NullPointerException,IOException,NumberFormatException{
	FileChooser fileChooser = new FileChooser();
	File workingDirectory = new File(System.getProperty("user.dir"));
	fileChooser.setInitialDirectory(workingDirectory);
	FileChooser.ExtensionFilter extFilter = 
					new FileChooser.ExtensionFilter("FILES: (*.txt)(*.bin)", "*.txt", "*.bin");
	fileChooser.getExtensionFilters().add(extFilter);
	
	File file = fileChooser.showOpenDialog(primaryStage);
		
	String fileName = file.getName();
	String extension = fileName.substring(fileName.lastIndexOf("."));
	switch(extension){
		case ".txt":
			loadMainSoftware(file);
			break;
		case ".bin":
			loadMainSoftwareBin(file);
			break;
		default:
			System.out.println("Extensión de archivo desconocida");
			break;
	}        
}

private File chooseFileToSave() {
	FileChooser fileChooser = new FileChooser();
	return fileChooser.showSaveDialog(primaryStage);
}
	
	
	