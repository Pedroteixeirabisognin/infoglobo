
export default class Noticia {

    constructor(id, titulo, conteudo, dataPublicacao, links) {

        this._id = id;
        this._titulo = titulo;
        this._conteudo = conteudo;
        this._dataPublicacao = dataPublicacao;
        this._links = links
        Object.freeze(this);
    }

    get id() {
        return this._id;
    }

    get titulo() {
        return this._titulo;
    }

    get conteudo() {
        return this._conteudo;
    }

    get dataPublicacao() {
        return new Date(this.dataPublicacao);
    }

    get links() {
        return this._links;
    }

}