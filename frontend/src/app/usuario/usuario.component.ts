import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { Usuario } from '../dto/dtos';
import { UsuarioService } from '../shared/usuario.service';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  usuario : Usuario = new Usuario();
  usuarios : Usuario[]; 

  constructor(public usuarioService:UsuarioService){}

  ngOnInit() { 
    this.obtenerUsuarios()
  }


  registrarUsuario(){
    Swal.showLoading();
    let context = this;
    return this.usuarioService.PostUsuario(this.usuario).subscribe((data: {}) => {
      Swal.close();
      context.obtenerUsuarios();
    })
  }

  obtenerUsuarios(){
    Swal.showLoading();
    let context = this;
    return this.usuarioService.GetUsuarios().subscribe((data: []) => {
      context.usuarios = data;
      Swal.close();
    })
  }

}
