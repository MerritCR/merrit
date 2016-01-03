/**
 * Created by josh on 12/12/15.
 */
import {Component} from 'angular2/core';
import {Menu} from './menu'

@Component({
    selector: 'header-component',
    template:`
        <img src="./img/Eclipse-logo-2014.svg">
        <menu></menu>
        <h1>Main page for Gerrit review.</h1>
    `,
    directives: [Menu]
})

export class HeaderComponent {

}
