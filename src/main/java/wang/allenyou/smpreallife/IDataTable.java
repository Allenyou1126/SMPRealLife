package wang.allenyou.smpreallife;

import java.util.List;

public interface IDataTable {
	int getLife(String playerName);
	void setLife(String playerName, int targetLife);

	List<String> getAllPlayerNames();

	boolean contains(String playerName);
}
