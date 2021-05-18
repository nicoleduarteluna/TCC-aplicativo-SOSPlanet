const denunciaList = document.querySelector('#denuncia-List');
const form = document.querySelector('#add-denuncia-form');

//create element and render cafe 

function renderDenuncia(doc){
	let li = document.createElement('li');
	let descricao = document.createElement('span');
	let localizacao = document.createElement('span');
	let titulo = document.createElement('span');
	let observacao = document.createElement('span');
	let situacao = document.createElement('span');
	let dataConclusao = document.createElement('span');
	let dataConclusaoTxt = document.createElement('span');
	let dataInicioTxt = document.createElement('span');
	let dataInicio = document.createElement('span');
	let cross = document.createElement('div');
	let info = document.createElement('div');

	
	li.setAttribute('data-id', doc.id);
	titulo.textContent = doc.data().titulo;
	descricao.textContent = doc.data().descricao;
	localizacao.textContent = doc.data().localizacao;
	observacao.textContent = doc.data().observacao;
	situacao.textContent = doc.data().situacao;
	dataInicio.textContent = doc.data().dataInicio;
	dataConclusao.textContent = doc.data().dataConclusao;
	cross.textContent = 'Concluir';
	info.textContent = 'Adicionar informações';
	dataInicioTxt.textContent = 'Data de ínicio: ' ;
	dataConclusaoTxt.textContent = 'Data de finalização: ';

	
	// o segundo name e city foi por causa do firebase
	
	
	li.appendChild(titulo);
	li.appendChild(descricao);
	li.appendChild(localizacao);
	li.appendChild(observacao);
	li.appendChild(situacao);
	li.appendChild(dataInicioTxt)
	li.appendChild(dataInicio);
	li.appendChild(dataConclusaoTxt);
	li.appendChild(dataConclusao);
	li.appendChild(info);
	li.appendChild(cross);
	
	denunciaList.appendChild(li);
	
	
	//deleting data
	cross.addEventListener('click', (e) => {
		e.stopPropagation();
		let id = e.target.parentElement.getAttribute('data-id');
	//	db.collection('denuncias').doc(id).delete();
		db.collection('Finalizadas').doc(id).update({
			observacao:form.observacao.value,
			situacao:form.situacao.value
		})	
			firebase.firestore().collection("Finalizadas").doc(id).update({
	//		dataConclusao: firebase.firestore.FieldValue.serverTimestamp()
			dataConclusao:	new Date(firebase.firestore.Timestamp.now().seconds*1000).toLocaleDateString()
			})
				.then(function(docRef) {
					console.log("Document written with ID: ", docRef.id);
				})
				.catch(function(error) {
					console.error("Error: ", error);
				});
	})
	
	info.addEventListener('click', (e) => {
		e.stopPropagation();
		let id = e.target.parentElement.getAttribute('data-id');
	//	db.collection('denuncias').doc(id).delete();
		db.collection('Finalizadas').doc(id).update({
			observacao:form.observacao.value,
			situacao:form.situacao.value
		})	
		firebase.firestore().collection("Finalizadas").doc(id).update({
		//	dataInicio: firebase.firestore.FieldValue.serverTimestamp().fromDate()
			dataInicio:	new Date(firebase.firestore.Timestamp.now().seconds*1000).toLocaleDateString()
			})
				.then(function(docRef) {
					console.log("Document written with ID: ", docRef.id);
				})
				.catch(function(error) {
					console.error("Error: ", error);
				});
				form.observacao.value = '';
				form.situacao.value = '';	
	})

}

	db.collection('denuncias').orderBy('titulo').get().then((snapshot) => {
	snapshot.docs.forEach(doc => {
		renderDenuncia(doc);
	})

})


//form.addEventListener('submit', (e) => {
//	e.preventDefault();
//	db.collection('denuncias').add({
//		observacao:form.observacao.value,
//		situacao:form.situacao.value
//	});
//	form.observacao.value = '';
//	form.situacao.value = '';
// })