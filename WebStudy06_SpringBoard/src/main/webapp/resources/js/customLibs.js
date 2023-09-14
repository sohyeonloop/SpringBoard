/**
 * 
 */
$.fn.serializeObject = function() {

	let data = {};

	let nameSet = new Set();
	//this is jQuery Object
	this.find(":input[name]").each((idx, ipt) => {
		nameSet.add(ipt.name)

	}); //제이쿼리 익스텐션 구조 : 모든 input 태그 다옴 [name](attribute selector) 이있는 친구만 데려오기  
	let $formThis = this; //화살표 함수에서 this 문제 때문에 만든것 

	nameSet.forEach(n => { //화살표 함수에서 this는 window 잘 기억해 놔!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		let $ipt = $formThis.find(`:input[name=${n}]`);
		let type = $ipt.attr("type");
		let value = null;
		if (type == "radio") {

			$ipt.filter((idx, rdoIpt) => { return rdoIpt.checked; }).val(); // rdo버튼이라서 값이 하나라서 가능 
		} else if (type == "checkbox") {
			$ipt.filter((idx, chkIpt) => chkIpt.checked)
				.get()
				.map(i => i.value);
		} else if (type == "number") {
			value = $ipt.val(); //value 속성값을 반환 하기 때문에 String. (attribute는 String 타입.) 
			value = parseInt(value);
		} else {
			value = $ipt.val(); //value 속성값을 반환 하기 때문에 String. (attribute는 String 타입.) 
		}

		data[n] = value;
	});
	return data;
}

$.timeFormat = function(time) {
	// formatting : 1:59
	if ((time || time == 0) && time >= 0) {
		let minute = Math.trunc(time / 60);
		let second = time % 60;
		return `${new String(minute).padStart(2, '0')}:${new String(second).padStart(2, '0')}`;
	} else { //없거나 0보다 작거나
		throw new Error("시간 데이터는 0이상의 값이 필요함.");
	}
}

const speed = 100;

$.fn.timer = function() {
	this.each(function(index, element) {
		const $element = $(this);

		const timeout = $element.data("timeout");
		if (!timeout || !/^\d+$/.test(timeout)) throw new Error(`타이머는 (${timeout})숫자로 구성됨.`);
		/*타이머의 조건
		1. 타이머의 초기값
		2. 1초마다 타이머를 차감 
			1) 차감된 값을 유지할 수 있는 프로퍼티
			2) 차감을 시키기 위한 스케쥴링 작업
		3. 차감되는 타이머 값은 매 시점마다 타이머의 영역에 출력. 
		4. 타이머의 값은 02:00 과 같은 형태로 출력. 
		5. optinal : 필요하다면 타이머 영역 다음에 메시지 창을 생성함., 해당 메시지는 1분 남은 시점에 렌더링함.
			1) 메시지 영역 프로퍼티 추가
			2) 메시지 처리를 위한 지연 작업 스케쥴링. 
		*/


		function TimerInfo(timeout, timeArea, msgFlag = false) {
			/*생성자 여기의 this는 timerInfo얘라고..?*/
			this.timeout = timeout - 1;
			this.timerArea = timeArea;

			let _makeMessageDiv = function(element) {
				let $messageDiv = $("<div>").addClass("message-area").append(
					$("<p>").html("세션 여장 여부 확인"),
					$("<input>").attr({
						type: "button",
						value: "예"
					}).addClass("controlBtn").data("role", "yesBtn"),
					$("<input>").attr({
						type: "button",
						value: "아니오"
					}).addClass("controlBtn").data("role", "noBtn")

				).on("click", ".controlBtn", function(event) {
					let $btn = $(event.target);
					let role = $btn.data('role');
					if (role == 'yesBtn') {

						$.ajax({
							url: element.dataset.requestUrl ?? "",
							method: "head",
						}).done(() => {
							// 타이머값도 초기화, 메시지 작업도 초기화. 
							element._timerInfo.init();
						});
					}
					$btn.parents(".message-area").remove();
					/*delete element._timerInfo.$messageArea
					delete element._timerInfo.messageJob*/

				}).hide().insertAfter(element);
				return $messageDiv;
			}
			this.init = function() {
				this.timer = timeout;

				if (msgFlag) {
					this.$messageArea = _makeMessageDiv(this.timerArea);
					this.messageJob = setTimeout((function() {
						this.$messageArea.show();
					}).bind(this), (this.timeout - 60) * speed);
				}
			}
			this.destroy = function() {
				clearInterval(this.timerJob);
				if (this.$messageArea) {
					this.$messageArea.remove();
					clearTimeout(this.messageJob);
				}
				console.log("delete 전 : ", this.timerArea._timerInfo);
				delete this.timerArea._timerInfo;
				console.log("delete 후 : ", this.timerArea._timerInfo);
			}

			this.init();

			this.timerJob = setInterval((function() {
				if (this.timer <= 0) {
					this.destroy();
				} else {
					this.timerArea.innerHTML = $.timeFormat(--this.timer);
				}
			}).bind(this), 1 * speed);
		}
		let msgFlag = $element.data("msgFlag");
		element._timerInfo = new TimerInfo(timeout, element, msgFlag);
		console.log(element._timerInfo);
	});
	return this;
}

/*$(document).ready(function(){});
$(document).on("ready", function(){});*/
$(function() {
	let $span = $("[data-timeout]").timer();
});