package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

public class ProjectsApp {
	
	//Initialize the scanner and curProject objects for later use
	Project curProject;
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();
	
	// @formatter:off
	private List<String> operations = List.of( 
			//show the user the options
			"1) Add a project",
			"2) List projects",
			"3) Select a project",
			"4) Update project details",
			"5) Delete a project"
			);
	// @formatter:on
	
	public static void main(String[] args) {
		new ProjectsApp().processUserSelections();
	}


	private void processUserSelections() {
		// This method is the switch controller for the user's choice
		boolean done = false;
		while(!done) {
			try {
				int selection = getUserSelection();
				
				switch (selection){
				case -1 :
					done = exitMenu();
					break;
					
				case 1 :
					createProject();
					break;
					
				case 2 :
					listProjects();
					break;
					
				case 3 :
					selectProject();
					break;
					
				case 4 :
					updateProjectDetails();
					break;
					
				case 5 :
					deleteProject();
					break;
					
				default:
					System.out.println("\n" + selection + " is not a valid option. Try again"); // does not space correctly?
					break;
					}
				}
			catch(Exception e) {
				System.out.println("\nError:" + e + " Try again.");
			}
		}
	}

	private void deleteProject() {
		//deletes a project that the user chooses with a project ID
		listProjects();
		
		Integer projectId = getIntInput("Enter a project ID you wish to remove");
		
		projectService.deleteProject(projectId);
		System.out.println("Project " + projectId + " was deleted successfully.");
		
		if(Objects.nonNull(curProject) && curProject.getProjectId().equals(projectId)){
			curProject = null;
		}
	}
	
	private void updateProjectDetails() {
		//after the user selects a project, it can then be edited with this method
		if(Objects.isNull(curProject)) { //handler for if the user doesn't select a project first
			System.out.println("Please select a project");
			return;
		}
		//this block retrieves the selected project's information and displays it for the user
		String projectName = getStringInput("Enter the project name [" + curProject.getProjectName() + "]");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours [" + curProject.getEstimatedHours() + "]");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours [" + curProject.getActualHours() + "]");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5) [" + curProject.getDifficulty() + "]");
		String notes = getStringInput("Enter the project notes [" + curProject.getNotes() + "]");
		
		Project project = new Project();
		project.setProjectId(curProject.getProjectId());
		project.setProjectName(Objects.isNull(projectName) ? curProject.getProjectName() : projectName);
		project.setEstimatedHours(Objects.isNull(projectName) ? curProject.getEstimatedHours() : estimatedHours);
		project.setActualHours(Objects.isNull(actualHours) ? curProject.getActualHours() : actualHours);
		project.setDifficulty(Objects.isNull(difficulty) ? curProject.getDifficulty() : difficulty);
		project.setNotes(Objects.isNull(notes) ? curProject.getNotes(): notes);
		
		projectService.modifyProjectDetails(project);
		
		curProject = projectService.fetchProjectById(curProject.getProjectId());
		
	}

	private void selectProject() {
		//The selection method that resets the current project and loads in the one the user wanted.
		listProjects();
		Integer projectId = getIntInput("Enter a project ID to select a project");
		
		curProject = null;
		curProject = projectService.fetchProjectById(projectId);
		
	}


	private void listProjects() {
		// from a list of projects, print out every project
		List<Project> projects = projectService.fetchAllProjects();
		
		System.out.println("\nProjects:");
		projects.forEach(project -> System.out.println("    " + project.getProjectId() + ": " + project.getProjectName()));
	}


	private void createProject() {
		//creates a project with prompts for the user to enter
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getDecimalInput("Enter the actual hours");
		Integer difficulty = getIntInput("Enter the project difficulty (1-5)");
		String notes = getStringInput("Enter the project notes");
		
		Project project = new Project();
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully created the project: "+ dbProject);
	}


	private int getUserSelection() { 
		// print and get the user's input from the menu
		printOperations();
		
		Integer input = getIntInput("Enter a menu selection");
		return Objects.isNull(input) ? -1 : input;
	}


	private Integer getIntInput(String prompt) {
		//converts a presumed string number into a real integer
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return Integer.valueOf(input);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + "is not a valid number.");
		}
	}

	private void printOperations() {
		//shows the current list of operations 
		System.out.println("\nThese are the available selections. Press the Enter key to quit:");
		operations.forEach(line -> System.out.println("   " + line));
	
		if(Objects.isNull(curProject)) {
			System.out.println("\nYou are not working with a project.");
		}
		else {
			System.out.println("\nYou are working with project: "+ curProject);
		}
		
	}
	
	private String getStringInput(String prompt) {
		//filters user input
		System.out.print(prompt + ": ");
		String input = scanner.nextLine();
		
		return input.isBlank() ? null : input.trim();
	}

	private boolean exitMenu() { 
		// switches exitMenu to break out of the application
		System.out.println("Exiting the menu.");
		return true;
	}
	
	private BigDecimal getDecimalInput(String prompt) { 
		//processes the user's numeric input into a decimal format
		String input = getStringInput(prompt);
		
		if(Objects.isNull(input)) {
			return null;
		}
		try {
			return new BigDecimal(input).setScale(2);
		}
		catch(NumberFormatException e) {
			throw new DbException(input + "is not a valid decimal number.");
		}
	}
}


