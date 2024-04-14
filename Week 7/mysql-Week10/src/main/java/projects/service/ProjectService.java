package projects.service;

import java.util.List;
import java.util.NoSuchElementException;
import projects.dao.ProjectDao;
import projects.entity.Project;

public class ProjectService {
	//retrieves Dao data and processes it into projectDao instances
	
	private ProjectDao projectDao = new ProjectDao();
	
	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects();
	}

	public Project addProject(Project project) {
		return projectDao.insertProject(project);
	}
	public Project fetchProjectById(Integer projectId) {
		return projectDao.fetchProjectById(projectId).orElseThrow(() -> 
		new NoSuchElementException("Project with project ID=" + projectId + " does not exist. ")); 
		
	}

}
