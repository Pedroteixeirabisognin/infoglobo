export default class NoticiaService {


    constructor(http) {

        this.$http = http;

    }

    listAll() {

        return this.$http.get('noticia/').then(
            res => res.json(),
            err => {
                throw new Error('Não foi possível obter as noticias. Tente mais tarde');
            }
        )
    }



    findById(id) {
        return this.$http.get('noticia/' + id)
            .then(
                res => res.json(),
                err => {
                    if (err.status === 404) {
                        throw new Error('Noticia não encontrada!');
                    }

                    throw new Error('Erro ao contactar o servidor, por favor tente mais tarde!');
                }
            )
    }

    delete(id) {
        return this.$http
            .delete('noticia/' + id)
            .then(null, err => {
                throw new Error('Erro ao contactar o servidor, por favor tente mais tarde!');
            });
    }

    save(noticia) {
        return this.$http
            .post('noticia/', noticia)
            .then(null, err => {
                if (err.status === 400) {
                    throw new Error('Requisição inválida, verifique os campos!');
                }
                throw new Error('Erro ao contactar o servidor, por favor tente mais tarde!');
            });
    }


    update(noticia) {
        return this.$http
            .put('noticia/' + noticia.id, noticia)
            .then(null, err => {
                if (err.status === 400) {
                    throw new Error('Requisição inválida, verifique os campos!');
                }
                throw new Error('Erro ao contactar o servidor, por favor tente novamente!');
            });
    }

}