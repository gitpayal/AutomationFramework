/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GroovyScripts

def name='payal.verma'

println "Hello $name!"

 List<String> searchFile() {
    List<String> files = []
    File rootFolder = new File('C:/')

    rootFolder.eachFileRecurse(FileType.FILES) {
        if (it.name('firefox.exe')) {
            files << it.name
        }
    }

    return files
}
