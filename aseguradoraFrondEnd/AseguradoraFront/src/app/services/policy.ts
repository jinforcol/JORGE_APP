import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class Policy {
   private apiUrl = 'http://localhost:9090';

   constructor(private http: HttpClient) {}

   //getpolicies
   getPolicies(): Observable<Policy[]> {
      return this.http.get<Policy[]>(this.apiUrl)//(`${this.apiUrl}/policies`);
   }

   
}
