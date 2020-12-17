import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { Tarea } from '../dto/dtos';
import { UsuarioService } from '../shared/usuario.service';
import {Sort} from '@angular/material/sort';

@Component({
  selector: 'app-tarea',
  templateUrl: './tarea.component.html',
  styleUrls: ['./tarea.component.css']
})
export class TareaComponent implements OnInit {

  usuarioId : number;

  tarea : Tarea = new Tarea();
  tareas : Tarea[]; 

  fechaCreacion : string = null;
  estado : string = null;
  dirOrder : string = null;
  campoOrder : string = null;

  constructor(public usuarioService:UsuarioService, private route:ActivatedRoute){
      console.log("Hera I'm");
  }

  ngOnInit() {       
    this.usuarioId = Number(this.route.snapshot.paramMap.get("usuarioId"));
    this.obtenerTareas()
  }


  registrarTarea(){
    Swal.showLoading();
    let context = this;
    return this.usuarioService.PostTarea(this.tarea, this.usuarioId).subscribe((data: {}) => {
      Swal.close();
      this.fechaCreacion = null;
      this.estado = null;
      context.obtenerTareas();
    })
  }

  obtenerTareas(){
    Swal.showLoading();
    let context = this;

    var valor = "null";
    var campo = "null";
    if(this.fechaCreacion != null && this.fechaCreacion != "") { 
        campo = "fechaCreacion";
        valor = this.fechaCreacion;
    }else if(this.estado != null && this.estado != "") { 
        campo = "estado";
        valor = this.estado;
    }

    return this.usuarioService.FiltrarTareas(this.usuarioId, campo, valor, this.checkField(this.campoOrder), this.checkField(this.dirOrder)).subscribe((data: []) => {
      context.tareas = data;
      Swal.close();
    })
  }

  checkField(values : string){
    if(values == null || values == ''){
      return "null";
    }
    return values;
  }

  sortData(sort: Sort) {
    this.dirOrder = sort.direction;
    this.campoOrder = sort.active;
    this.obtenerTareas();
  }

}
