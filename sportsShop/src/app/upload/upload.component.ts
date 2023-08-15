import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent {

  selectedFile!: File;
  resMessage: any="";
  imageName: any;
  name:string="";
  game:string="";
  qty:string="";
  company:string="";
  price:string="";
  warrenty:string="";


  constructor(private http:HttpClient){}
  ngOnInit(){
    this.selectedFile={} as any;
  }

  //Gets called when the user selects an image
  public onFileChanged(event:any) {
    this.selectedFile = event.target.files[0];
  }


  prdSubmitt(){
    
    const uploadImageData = new FormData();

    uploadImageData.append('dietFile', this.selectedFile, this.selectedFile.name);
    uploadImageData.append("name",this.name);
    uploadImageData.append("game",this.game);
    uploadImageData.append("qty",this.qty);
    uploadImageData.append("company",this.company);
    uploadImageData.append("price",this.price);
    uploadImageData.append("warrenty",this.warrenty);
    
    

    let res =this.http.post("http://localhost:1234/equip/add",uploadImageData,
    {responseType:'text' as 'json'});
    res.subscribe(
      data=>{
        this.resMessage = data;
        console.log(data);
        this.name="";
        this.game="";
        this.qty="";
        this.company="";
        this.price="";
        this.warrenty="";
        
      }
    );

  }

}