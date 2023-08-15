import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent {

  allEquip:any

  constructor(private http:HttpClient){}

  ngOnInit(){
    let res = this.http.get("http://localhost:1234/equip/all");
    res.subscribe(
      data=>this.allEquip=data
    );

  }

  delteUser(id:string){
console.log(id);
let res = this.http.get("http://localhost:1234/equip/delete?id="+id);
    res.subscribe(
      data=>this.allEquip=data
    );
  }

}
