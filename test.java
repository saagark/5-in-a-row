package ProgAssignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class test {
	public static void main(String[] args) {
		String[] colors = { "MAGENTA", "RED", "WHITE", "BLUE", "CYAN" };
		List<String> list = new ArrayList<String>();
		for (String color : colors)
			list.add(color);
		String[] removeColors = { "RED", "WHITE", "BLUE" };
		List<String> removeList = new ArrayList<String>();
		for (String color : removeColors)
			removeList.add(color);
		removeColors(list, removeList);
	}

	private	static	void	removeColors(	Collection<String>	collection1,	Collection<String>	collection2	)	{
		Iterator<	String	>	iterator	=	collection1.iterator();		
		while	(	iterator.hasNext()	)	{
			String tes = iterator.next();
		if	(	collection2.contains(	tes	)	)	
			 	System.out.println(tes);
		}
		}
}
