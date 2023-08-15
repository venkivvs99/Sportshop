package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins="*")
public class EquipController {
	@Autowired
	EquipRepo equiprepo;
	
	@GetMapping("/equip/find")
	public Equip findById(@RequestParam int id)
	{
		Equip equip=equiprepo.findById(id).get();
		 
		equip.setImage(decompressBytes(equip.getImage()));
		return equip;
	}
	@GetMapping("/equip/delete")
	public List<Equip> dleteById(@RequestParam int id)
	{
		equiprepo.deleteById(id);
		return getAllProducts();
		
		
	}
	@PostMapping("/equip/add")
	public String addEquip(@RequestParam ("dietFile") MultipartFile myFile,
			String name,
			String game,
			String qty,
			String company,
			String price,
			String warrenty
			) {
		try {
			Equip prdImage = new Equip(0, name,game,qty,company,price,warrenty,
					compressBytes(myFile.getBytes()));
			equiprepo.save(prdImage);
		}
		catch(Exception e) {
			
		}
		
		return "Successfully Added new Product";
	}
	
	@GetMapping("/equip/all")
	public List<Equip> getAllProducts()
	{
		List<Equip> eqList=new ArrayList<Equip>();
		
		List<Equip> resEqList=equiprepo.findAll();
		
		Equip equip=null;
		for(int i=0;i<resEqList.size();i++)
		{
			equip=resEqList.get(i);
			
			equip.setImage(decompressBytes(equip.getImage()));
			
			eqList.add(equip);
			
		}
		return eqList;
	}
	
	public static byte[] compressBytes(byte[] data)
	{
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}
	
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
	
}
