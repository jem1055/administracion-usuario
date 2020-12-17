import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TareaComponent } from './tarea/tarea.component';
import { UsuarioComponent } from './usuario/usuario.component';


const routes: Routes = [
  { path: '', component : UsuarioComponent},
  { path: 'tareas/:usuarioId', component : TareaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
