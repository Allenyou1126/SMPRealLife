package wang.allenyou.smpreallife;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class RuntimeDataTable implements IDataTable {

	private Map<String, Integer> dataSheet;

	public RuntimeDataTable() {
		this(null);
	}

	public RuntimeDataTable(IDataTable target) {
		dataSheet = new HashMap<>();
		if (!(Objects.isNull(target))) {
			List<String> list = target.getAllPlayerNames();
			list.forEach(s -> dataSheet.put(s, target.getLife(s)));
		}
	}

	@Override
	public int getLife(String playerName) {
		if (!(this.contains(playerName))) {
			return 0;
		}
		return dataSheet.get(playerName);
	}

	@Override
	public void setLife(String playerName, int targetLife) {
		if (targetLife < 0) {
			targetLife = 0;
		}
		if (!(this.contains(playerName))) {
			dataSheet.put(playerName, targetLife);
		} else {
			dataSheet.replace(playerName, targetLife);
		}
	}

	@Override
	public List<String> getAllPlayerNames() {
		return dataSheet.keySet().stream().toList();
	}

	@Override
	public boolean contains(String playerName) {
		return dataSheet.containsKey(playerName);
	}
}
