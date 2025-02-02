package com.java8;

import com.java8.ExternalDepo;
import com.java8.InvoiceComponent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Java8_4_HashMap {
	
	public static void main(String[] args) {
		
		Map<String,Integer> empMap= new HashMap<>();
		empMap.put("Anil",20);
		empMap.put("Diki",50);
		empMap.put("Churan",70);
		empMap.put("Bishnu",10);
		
		//filter where age >30
		Map<String,Integer> filterMap = empMap.entrySet().stream().filter(e->e.getValue()>30).collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));
		System.out.println(filterMap);
		empMap.entrySet().stream().filter(e->e.getValue()>30).collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
		
		
		
		Map<String, String> cityMap = new HashMap<>();
		cityMap.put("Tokyo", "Japan");
		cityMap.put("Berlin", "Germany");
		cityMap.put("Kyoto", "Japan");
		cityMap.put("Belgrade", "Serbia");
		cityMap.put("Madrid", "Spain");
		
		// filter the map where value =Japan
		Map<String, String> japancityMap = cityMap.entrySet().stream().filter(e->"Japan".equalsIgnoreCase(e.getValue())).collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
		System.out.println(japancityMap);
		
		Map<String, String> filtercityMap = cityMap.entrySet().stream().filter(e-> "Japan".equalsIgnoreCase(e.getValue()) || "Serbia".equalsIgnoreCase(e.getValue()) ).collect(Collectors.toMap(e->e.getKey(),e->e.getValue()));
		System.out.println(filtercityMap);
		
		//output the keys as a string
		String keystr = cityMap.entrySet().stream().filter(e-> "Japan".equalsIgnoreCase(e.getValue()) || "Serbia".equalsIgnoreCase(e.getValue()) ).map(e->e.getKey()).collect(Collectors.joining(" , "));
		System.out.println(keystr);
		
		
		cityMap.entrySet().stream().filter(e->e.getValue().equals("Japan")).forEach(e->System.out.println(e.getKey() + e.getValue()));
		
		//remove duplicates from list
		
		List<Integer> intList =  List.of(1,2,3,4,4,5,5,2,2,3,4,6,7,8,4);
		
		//type1
		List<Integer> filteredintList = intList.stream().distinct().collect(Collectors.toList());
		System.out.println(filteredintList);
		
		//type2
		List<Integer> filterlist = new ArrayList<>();
		for(Integer i : intList) {
			if(!filterlist.contains(i)) {
				filterlist.add(i);
			}
		}
		System.out.println(filterlist);


		//check if two maps are same
		List<InvoiceComponent> componentList = List.of(
				new InvoiceComponent(1L,new BigDecimal(500)),
                new InvoiceComponent(2L, new BigDecimal(1000))
		     );

		List<ExternalDepo> externalDepoList = List.of(
				new ExternalDepo(100L,1L, new BigDecimal(100)),
				new ExternalDepo(200L,1L, new BigDecimal(300)),
				new ExternalDepo(300L,1L, new BigDecimal(200)),

				new ExternalDepo(400L,2L, new BigDecimal(500)),
				new ExternalDepo(500L,2L, new BigDecimal(500))
				);

		Map<Long,BigDecimal> componentMap = componentList.stream().collect(Collectors.toMap(InvoiceComponent::getCompId,InvoiceComponent::getAmount));
		
		Map<Long,BigDecimal> externalDepoMap = externalDepoList.stream().collect(Collectors.groupingBy(ExternalDepo::getCompId,Collectors.reducing(BigDecimal.ZERO,ExternalDepo::getAmount,BigDecimal::add)));

		System.out.println("componentMap :: " + componentMap);
		System.out.println("externalDepoMap :: " + externalDepoMap);

		if(componentMap.size()!=externalDepoMap.size()){
			System.out.println("Maps are not same !!! ");
		}
		boolean isSame = componentMap.entrySet().stream().allMatch(e->e.getValue().equals(externalDepoMap.get(e.getKey())));
		if(isSame){
			System.out.println("Maps are same !!! ");
		}else {
			System.out.println("Maps are not same !!! ");
		}
		
	}

}
