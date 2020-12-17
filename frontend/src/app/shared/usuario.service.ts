import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError, map } from 'rxjs/operators';
import { Tarea, Usuario } from '../dto/dtos';
import Swal from 'sweetalert2'


@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  baseurl = 'http://localhost:8080';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin' : '*'
    })
  }

  constructor(private http: HttpClient) { }

  PostTarea(tarea : Tarea, usuarioId : number) : Observable<Tarea>{
    return this.http.post<Tarea>(this.baseurl + "/tareas/crear/" + usuarioId, tarea, this.httpOptions).pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  FiltrarTareas(usuarioId : number, campo : string, valor : string, columnaOrder : string, dirOrdenamiento : string ){
    return this.http.get<Usuario[]>(this.baseurl + '/tareas/filtrar/' + usuarioId + '/' + campo + '/' + valor + '/' + columnaOrder + '/' + dirOrdenamiento, this.httpOptions)
    .pipe(
      map(result => result),
      catchError(this.errorHandl)
    )
  }

  PostUsuario(usuario: Usuario) : Observable<Usuario> {
    return this.http.post<Usuario>(this.baseurl + "/usuarios/crear", usuario, this.httpOptions).pipe(
      retry(1),
      catchError(this.errorHandl)
    )
  }

  GetUsuarios(): Observable<Usuario[]> {

    return this.http.get<Usuario[]>(this.baseurl + '/usuarios/', this.httpOptions)
    .pipe(
      map(result => result),
      catchError(this.errorHandl)
    )
  }

  // Error handling
  errorHandl(error) {
    Swal.close();
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: 'Hay un problema en el servido!'
    })
    let errorMessage = '';
    if(error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
 }


}
